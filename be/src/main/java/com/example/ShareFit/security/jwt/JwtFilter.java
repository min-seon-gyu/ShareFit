package com.example.ShareFit.security.jwt;

import com.example.ShareFit.security.CustomUserDetails;
import com.example.ShareFit.security.MemberSecurity;
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
    private static final String LOGIN_URL = "/auth/login";
    private static final String REISSUE_URL = "/auth/refresh";
    private static final String HEALTH_URL = "/health";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        if (requestURI.equals(HEALTH_URL) || requestURI.equals(LOGIN_URL) || requestURI.equals(REISSUE_URL)) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken= request.getHeader("Authorization");
        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
            PrintWriter writer = response.getWriter();
            writer.write("Invalid Access Token");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
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

        String uuid = jwtUtil.getUuid(token);
        String role = jwtUtil.getRole(token);

        MemberSecurity memberSecurity = MemberSecurity.builder()
                        .uuid(uuid)
                        .role(role)
                        .build();

        CustomUserDetails customUserDetails = new CustomUserDetails(memberSecurity);
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
