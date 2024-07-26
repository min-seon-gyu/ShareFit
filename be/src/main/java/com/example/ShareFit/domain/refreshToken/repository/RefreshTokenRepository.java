package com.example.ShareFit.domain.refreshToken.repository;

import com.example.ShareFit.domain.refreshToken.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Repository
public class RefreshTokenRepository {
    private final RedisTemplate<String, String> redisTemplate;
    @Value("${spring.jwt.refreshToken_expiration_time}")
    private Long refreshTokenExpiredMs;

    public void create(RefreshToken refreshToken){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(refreshToken.getRefreshToken(), refreshToken.getUuid(), refreshTokenExpiredMs, TimeUnit.MILLISECONDS);
    }

    public void delete(String refreshToken){
        redisTemplate.delete(refreshToken);
    }

    public boolean exist(String refreshToken){
        return redisTemplate.hasKey(refreshToken);
    }
}
