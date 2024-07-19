package com.example.ShareFit.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "회원 조회 응답 DTO")
public class MemberResponseDto {
    @Schema(description = "회원 ID")
    private Long id;
    @Schema(description = "회원 UUID")
    private String uuid;
    @Schema(description = "회원 닉네임")
    private String nickname;
    @Schema(description = "회원 이미지 경로")
    private String imagePath;
    @Schema(description = "회원 권한")
    private String role;
}
