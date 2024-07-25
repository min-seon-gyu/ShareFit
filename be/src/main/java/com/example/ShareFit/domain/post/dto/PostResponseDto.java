package com.example.ShareFit.domain.post.dto;

import com.example.ShareFit.domain.like.Like;
import com.example.ShareFit.domain.post.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 조회 응답 DTO")
public class PostResponseDto {
    @Schema(description = "게시글 ID")
    private Long id;
    @Schema(description = "게시글 내용")
    private String content;
    @Schema(description = "게시글 이미지 경로")
    private String imagePath;
    @Schema(description = "게시글 좋아요 수")
    private Long totalLike;
    @Schema(description = "게시글 좋아요 유무")
    private Boolean isLike;
    @Schema(description = "게시글 작성 회원 ID")
    private Long memberId;
    @Schema(description = "게시글 작성 회원 닉네임")
    private String nickname;
    @Schema(description = "게시글 작성 회원 프로필 경로")
    private String profilePath;
    public PostResponseDto(Post post, Long userId){
        this.id = post.getId();
        this.content = post.getContent();
        this.imagePath = post.getImagePath();
        this.totalLike = post.getTotalLike();
        this.memberId = post.getMember().getId();
        this.nickname = post.getMember().getNickname();
        this.profilePath = post.getMember().getProfilePath();
        this.isLike = isLike(post, userId);
    }

    private boolean isLike(Post post, Long userId){
        for (Like like : post.getLikes()) {
            if(like.getMember().getId() == userId && like.getPost().getId() == post.getId()){
                return true;
            }
        }
        return false;
    }
}
