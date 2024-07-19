package com.example.ShareFit.security.jwt;

import com.example.ShareFit.domain.member.dto.MemberResponseDto;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey;

    public JwtUtil(@Value("${spring.jwt.secret}") String secretKey) {
        this.secretKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public Long getId(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("id", Long.class);
    }

    public String getUuid(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("uuid", String.class);
    }

    public String getNickname(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("nickname", String.class);
    }

    public String getImagePath(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("imagePath", String.class);
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public String getCategory(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public MemberResponseDto getMemberResponseDto(String token){
        return MemberResponseDto.builder()
                .id(getId(token))
                .uuid(getUuid(token))
                .nickname(getNickname(token))
                .imagePath(getImagePath(token))
                .role(getRole(token))
                .build();
    }

    public String createJwt(String category, Long id, String uuid, String nickname, String imagePath, String role, Long expiredMs) {
        return Jwts.builder()
                .claim("category", category)
                .claim("id", id)
                .claim("uuid", uuid)
                .claim("nickname", nickname)
                .claim("imagePath", imagePath)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

    public String createJwt(String category, MemberResponseDto memberResponseDto, Long expiredMs) {
        return Jwts.builder()
                .claim("category", category)
                .claim("id", memberResponseDto.getId())
                .claim("uuid", memberResponseDto.getUuid())
                .claim("nickname", memberResponseDto.getNickname())
                .claim("role", memberResponseDto.getRole())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }
}
