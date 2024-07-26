package com.example.ShareFit.domain.comment.dto;

import com.example.ShareFit.domain.comment.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "댓글 응답 DTO")
public class CommentResponseDto {
    @Schema(description = "댓글 ID")
    private Long id;
    @Schema(description = "댓글 내용")
    private String content;
    @Schema(description = "댓글 작성 회원 ID")
    private Long memberId;
    @Schema(description = "댓글 작성 회원 닉네임")
    private String nickname;
    @Schema(description = "댓글 작성 회원 프로필 경로")
    private String profilePath;

    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.memberId = comment.getMember().getId();
        this.nickname = comment.getMember().getNickname();
        this.profilePath = comment.getMember().getProfilePath();
    }
}
