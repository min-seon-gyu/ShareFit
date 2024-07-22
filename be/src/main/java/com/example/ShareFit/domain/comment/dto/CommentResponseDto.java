package com.example.ShareFit.domain.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "댓글 응답 DTO")
public class CommentResponseDto {
    @Schema(description = "댓글 ID")
    private Long id;
    @Schema(description = "댓글 작성 회원 ID")
    private Long memberId;
    @Schema(description = "댓글 작성 회원 닉네임")
    private String nickname;
    @Schema(description = "댓글 내용")
    private String content;
}
