package com.example.ShareFit.domain.post.dto;

import com.example.ShareFit.domain.post.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 페이지 조회 응답 DTO")
public class PostPageResponseDto {
    @Schema(description = "게시글 수")
    private Integer totalCount;
    @Schema(description = "전체 페이지 수")
    private Integer totalPages;
    @Schema(description = "현재 페이지")
    private Integer currentPage;
    @Schema(description = "게시글 리스트")
    private List<PostResponseDto> posts;

    public PostPageResponseDto(Page<Post> posts, Long memberId){
        this.totalCount = (int) posts.getTotalElements();
        this.totalPages = posts.getTotalPages() - 1;
        this.currentPage = posts.getNumber();
        this.posts = posts.stream().map(p -> new PostResponseDto(p, memberId)).toList();
    }
}
