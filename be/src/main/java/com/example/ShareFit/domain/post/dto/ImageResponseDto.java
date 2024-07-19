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
@Schema(description = "이미지 등록 응답 DTO")
public class ImageResponseDto {
    @Schema(description = "게시글 이미지 경로")
    private String imagePath;
}
