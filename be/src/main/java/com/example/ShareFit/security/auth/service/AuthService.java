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
            memberResponseDto = memberService.save(authRequestDto.getUuid(), authRequestDto.getNickname(), authRequestDto.getProfilePath());
        }

        String accessToken = jwtUtil.createJwt("access", memberResponseDto, accessTokenExpiredMs);
        String refreshToken = jwtUtil.createJwt("refresh", memberResponseDto, refreshTokenExpiredMs);

        response.addCookie(createCookie("refresh", refreshToken));

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
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        for(Cookie cookie : cookies){
            if(cookie.getName().equals("refresh")){
                refreshToken = cookie.getValue();
                break;
            }
        }

        if(refreshToken == null){
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        try {
            jwtUtil.isExpired(refreshToken);
        }
        catch (ExpiredJwtException e){
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        String category = jwtUtil.getCategory(refreshToken);
        if(!category.equals("refresh")){
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        if(!refreshTokenRepository.exist(refreshToken)){
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }

        refreshTokenRepository.delete(refreshToken);

        MemberResponseDto memberResponseDto = jwtUtil.getMemberResponseDto(refreshToken);

        String accessToken = jwtUtil.createJwt("access", memberResponseDto, accessTokenExpiredMs);
        refreshToken = jwtUtil.createJwt("refresh", memberResponseDto, refreshTokenExpiredMs);

        response.addCookie(createCookie("refresh", refreshToken));

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