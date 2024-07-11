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
@Schema(description = "인증 DTO")
public class AuthRequestDto {
    @Schema(description = "회원 UUID")
    String uuid;
    @Schema(description = "회원 닉네임")
    String nickname;
}
