package com.example.ShareFit.domain.comment.controller;

import com.example.ShareFit.common.swagger.CommentControllerDocs;
import com.example.ShareFit.domain.comment.dto.CommentAllResponseDto;
import com.example.ShareFit.domain.comment.dto.CommentCreateDto;
import com.example.ShareFit.domain.comment.dto.CommentResponseDto;
import com.example.ShareFit.domain.comment.dto.CommentUpdateDto;
import com.example.ShareFit.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController implements CommentControllerDocs {
    private final CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<CommentResponseDto> create(@RequestHeader("Authorization") String authorizationHeader,
                                                     @RequestBody CommentCreateDto commentCreateDto) {
        String accessToken = authorizationHeader.split("\\s")[1];
        CommentResponseDto commentResponseDto = commentService.create(accessToken, commentCreateDto);
        return ResponseEntity.ok(commentResponseDto);
    }

    @PatchMapping("/comment")
    public ResponseEntity<CommentResponseDto> update(@RequestBody CommentUpdateDto commentUpdateDto) {
        CommentResponseDto commentResponseDto = commentService.update(commentUpdateDto);
        return ResponseEntity.ok(commentResponseDto);
    }

    @DeleteMapping("/comment/{comment_id}")
    public ResponseEntity<Void> delete(@PathVariable("comment_id") Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
