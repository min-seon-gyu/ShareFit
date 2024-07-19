package com.example.ShareFit.security;

import com.example.ShareFit.domain.refreshToken.RefreshToken;
import com.example.ShareFit.domain.refreshToken.repository.RefreshTokenRepository;
import com.example.ShareFit.security.jwt.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    @Value("${spring.jwt.refreshToken_expiration_time}")
    private Long refreshTokenExpiredMs;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ShareFitOAuth2User shareFitOAuth2User = (ShareFitOAuth2User) authentication.getPrincipal();

        Long id = shareFitOAuth2User.getId();
        String uuid = shareFitOAuth2User.getName();
        String nickname = shareFitOAuth2User.getNickname();
        String imagePath = shareFitOAuth2User.getImagePath();
        String role = shareFitOAuth2User.getRole();

        String refreshToken = jwtUtil.createJwt("refresh", id, uuid, nickname, imagePath, role, refreshTokenExpiredMs);

        response.addCookie(createCookie("refresh", refreshToken));

        RefreshToken token = RefreshToken.builder()
                .uuid(uuid)
                .refreshToken(refreshToken)
                .build();

        refreshTokenRepository.save(token);
        response.sendRedirect("https://share-fit.vercel.app/auth/kakao");
    }

    private Cookie createCookie(String key, String value){
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setHttpOnly(true);
        cookie.setAttribute("SameSite", "None");
        cookie.setPath("/");
        cookie.setSecure(true);
        return cookie;
    }
}
