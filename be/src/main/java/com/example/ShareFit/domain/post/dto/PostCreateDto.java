package com.example.ShareFit.domain.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 등록 요청 DTO")
public class    PostCreateDto {
    @Schema(description = "게시글 내용")
    private String content;
    @Schema(description = "이미지 경로")
    private String imagePath;
}
