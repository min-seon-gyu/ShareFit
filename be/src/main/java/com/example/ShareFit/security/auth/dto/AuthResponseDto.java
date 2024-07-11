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
@Schema(description = "인증 반환 DTO")
public class AuthResponseDto {
    @Schema(description = "회원 사용가능 여부")
    private boolean available;
}
