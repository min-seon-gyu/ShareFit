package com.example.ShareFit.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "회원 수정 요청 DTO")
public class MemberUpdateDto {
    @Schema(description = "회원 닉네임")
    private String nickname;
}
