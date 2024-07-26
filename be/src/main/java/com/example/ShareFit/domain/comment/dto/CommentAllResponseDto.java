package com.example.ShareFit.domain.comment.dto;

import com.example.ShareFit.domain.comment.Comment;
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
@Schema(description = "전체 댓글 응답 DTO")
public class CommentAllResponseDto {
    @Schema(description = "댓글 수")
    private Integer totalCount;
    @Schema(description = "댓글 리스트")
    private List<CommentResponseDto> comments;

    public CommentAllResponseDto(List<Comment> comments) {
        this.totalCount = comments.size();
        this.comments = comments.stream().map(c -> new CommentResponseDto(c)).toList();
    }

    public CommentAllResponseDto (Post post){
        this.totalCount = post.getComments().size();
        this.comments = post.getComments().stream().map(c -> new CommentResponseDto(c)).toList();
    }
}
