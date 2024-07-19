package com.example.ShareFit.security.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "인증 응답 DTO")
public class AuthResponseDto {
    @Schema(description = "Access Token")
    private String accessToken;
}
