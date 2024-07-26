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
@Schema(description = "댓글 등록 요청 DTO")
public class CommentCreateDto {
    @Schema(description = "게시글 ID")
    private Long id;
    @Schema(description = "댓글 내용")
    private String content;
}
