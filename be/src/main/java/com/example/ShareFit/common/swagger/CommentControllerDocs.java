package com.example.ShareFit.common.swagger;

import com.example.ShareFit.domain.comment.dto.CommentCreateDto;
import com.example.ShareFit.domain.comment.dto.CommentResponseDto;
import com.example.ShareFit.domain.comment.dto.CommentUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "댓글 API")
public interface CommentControllerDocs {
    @Operation(summary = "댓글 등록", description = "Access Token, CommentCreateDto 활용하여 댓글 등록")
    @Parameter(name = "Access Token", description = "Authorization 헤더 Access Token")
    @Parameter(name = "CommentCreateDto", description = "댓글 등록 내용")
    ResponseEntity<CommentResponseDto> create(String authorizationHeader, CommentCreateDto commentCreateDto);
    @Operation(summary = "댓글 업데이트", description = "CommentUpdateDto 활용하여 댓글 업데이트")
    @Parameter(name = "CommentUpdateDto", description = "댓글 수정 내용")
    ResponseEntity<CommentResponseDto> update(CommentUpdateDto commentUpdateDto);
    @Operation(summary = "댓글 삭제", description = "댓글 ID 활용하여 게시글 삭제")
    @Parameter(name = "댓글 ID", description = "댓글 ID")
    ResponseEntity<Void> delete(Long id);
}
