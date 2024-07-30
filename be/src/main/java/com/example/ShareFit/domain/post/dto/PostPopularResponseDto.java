package com.example.ShareFit.domain.post.dto;

import com.example.ShareFit.domain.post.Post;
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
@Schema(description = "인기 게시글 리스트 조회 응답 DTO")
public class PostPopularResponseDto {
    @Schema(description = "인기 게시글 수")
    private Integer totalCount;
    @Schema(description = "인기 게시글 리스트 (3개)")
    private List<PostResponseDto> posts;

    public PostPopularResponseDto(List<Post> posts, Long memberId){
        this.totalCount = posts.size();
        this.posts = posts.stream().map(p -> new PostResponseDto(p, memberId)).toList();
    }
}
