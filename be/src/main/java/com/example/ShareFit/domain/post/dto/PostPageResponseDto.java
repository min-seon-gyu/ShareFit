package com.example.ShareFit.domain.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 페이지 조회 응답 DTO")
public class PostPageResponseDto {
    @Schema(description = "전체 게시글 수")
    private Integer totalCount;
    @Schema(description = "전체 페이지 수")
    private Integer totalPages;
    @Schema(description = "현재 페이지")
    private Integer currentPage;
    @Schema(description = "게시글 리스트")
    private List<PostResponseDto> posts;
}
