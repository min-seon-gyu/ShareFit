package com.example.ShareFit.security.jwt;

import com.example.ShareFit.security.MemberSecurity;
import com.example.ShareFit.security.ShareFitOAuth2User;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken= request.getHeader("Authorization");
        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = accessToken.split(" ")[1];

        try {
            jwtUtil.isExpired(token);
        }
        catch (ExpiredJwtException e){
            PrintWriter writer = response.getWriter();
            writer.write("Access Token Expired");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String category = jwtUtil.getCategory(token);
        if(!category.equals("access")){
            PrintWriter writer = response.getWriter();
            writer.write("Invalid Access Token");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        MemberSecurity memberSecurity = MemberSecurity.builder()
                .id(jwtUtil.getId(token))
                .uuid(jwtUtil.getUuid(token))
                .nickname(jwtUtil.getNickname(token))
                .imagePath(jwtUtil.getImagePath(token))
                .role(jwtUtil.getRole(token))
                .build();

        ShareFitOAuth2User shareFitOAuth2User = new ShareFitOAuth2User(memberSecurity);
        Authentication authToken = new UsernamePasswordAuthenticationToken(shareFitOAuth2User, null, shareFitOAuth2User.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
