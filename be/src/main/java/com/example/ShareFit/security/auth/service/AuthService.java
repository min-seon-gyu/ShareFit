package com.example.ShareFit.security.auth.service;

import com.example.ShareFit.domain.member.dto.MemberResponseDto;
import com.example.ShareFit.domain.member.service.MemberService;
import com.example.ShareFit.domain.refreshToken.RefreshToken;
import com.example.ShareFit.domain.refreshToken.repository.RefreshTokenRepository;
import com.example.ShareFit.security.auth.dto.AuthRequestDto;
import com.example.ShareFit.security.auth.dto.AuthResponseDto;
import com.example.ShareFit.security.jwt.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;
    private final MemberService memberService;
    private final RefreshTokenRepository refreshTokenRepository;
    @Value("${spring.jwt.accessToken_expiration_time}")
    private Long accessTokenExpiredMs;
    @Value("${spring.jwt.refreshToken_expiration_time}")
    private Long refreshTokenExpiredMs;
    public AuthResponseDto login(AuthRequestDto authRequestDto, HttpServletResponse response) {
        MemberResponseDto memberResponseDto = memberService.findByUuid(authRequestDto.getUuid());

        if(memberResponseDto == null){
            memberResponseDto = memberService.save(authRequestDto.getUuid(), authRequestDto.getNickname());
        }

        String accessToken = jwtUtil.createJwt("access", memberResponseDto, accessTokenExpiredMs);
        String refreshToken = jwtUtil.createJwt("refresh", memberResponseDto, refreshTokenExpiredMs);

        response.setHeader("Authorization", "Bearer " + accessToken);
        addCookie("refresh", refreshToken, response);

        RefreshToken token = RefreshToken.builder()
                .uuid(memberResponseDto.getUuid())
                .refreshToken(refreshToken)
                .build();

        refreshTokenRepository.save(token);

        AuthResponseDto authResponseDto = AuthResponseDto.builder()
                .accessToken(accessToken)
                .build();

        return authResponseDto;
    }

    public AuthResponseDto refresh(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = null;
        Cookie[] cookies = request.getCookies();

        if(cookies == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        for(Cookie cookie : cookies){
            if(cookie.getName().equals("refresh")){
                refreshToken = cookie.getValue();
                break;
            }
        }

        if(refreshToken == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        try {
            jwtUtil.isExpired(refreshToken);
        }
        catch (ExpiredJwtException e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        String category = jwtUtil.getCategory(refreshToken);
        if(!category.equals("refresh")){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        if(!refreshTokenRepository.exist(refreshToken)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        refreshTokenRepository.delete(refreshToken);

        MemberResponseDto memberResponseDto = jwtUtil.getMemberResponseDto(refreshToken);

        String accessToken = jwtUtil.createJwt("access", memberResponseDto, accessTokenExpiredMs);
        refreshToken = jwtUtil.createJwt("refresh", memberResponseDto, refreshTokenExpiredMs);

        RefreshToken token = RefreshToken.builder()
                .uuid(memberResponseDto.getUuid())
                .refreshToken(refreshToken)
                .build();

        refreshTokenRepository.save(token);

        response.setHeader("Authorization", "Bearer " + accessToken);
        addCookie("refresh", refreshToken, response);

        AuthResponseDto authResponseDto = AuthResponseDto.builder()
                .accessToken(accessToken)
                .build();

        return authResponseDto;
    }

    private void addCookie(String key, String value, HttpServletResponse response){
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setHttpOnly(true);

        response.addHeader("Set-Cookie", String.format("%s=%s; Path=/; Max-Age=%d; Secure; HttpOnly; SameSite=None",
                cookie.getName(), cookie.getValue(), cookie.getMaxAge()));
    }
}
