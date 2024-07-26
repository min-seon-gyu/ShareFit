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
@Schema(description = "댓글 업데이트 요청 DTO")
public class CommentUpdateDto {
    @Schema(description = "댓글 ID")
    private Long id;
    @Schema(description = "댓글 내용")
    private String content;
}
