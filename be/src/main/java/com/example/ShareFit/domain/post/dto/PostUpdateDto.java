package com.example.ShareFit.domain.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 수정 요청 DTO")
public class PostUpdateDto {
    @Schema(description = "게시글 내용")
    private String content;
    @Schema(description = "게시글 이미지 경로")
    private String imagePath;
}
