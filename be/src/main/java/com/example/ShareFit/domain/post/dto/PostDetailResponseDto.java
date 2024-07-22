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
@Schema(description = "게시글 상세 조회 응답 DTO")
public class PostDetailResponseDto {
    @Schema(description = "게시글 ID")
    private Long id;
    @Schema(description = "게시글 내용")
    private String content;
    @Schema(description = "게시글 이미지 경로")
    private String imagePath;
    @Schema(description = "게시글 좋아요 수")
    private Long likes;
    @Schema(description = "게시글 작성 회원 ID")
    private Long memberId;
    @Schema(description = "게시글 작성 회원 닉네임")
    private String nickname;
    @Schema(description = "게시글 작성 회원 프로필 경로")
    private String profilePath;
}
