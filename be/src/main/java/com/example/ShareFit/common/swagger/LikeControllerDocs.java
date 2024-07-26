package com.example.ShareFit.common.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "좋아요 API")
public interface LikeControllerDocs {
    @Operation(summary = "좋아요 추가", description = "게시글 좋아요 추가")
    @Parameter(name = "Access Token", description = "Authorization 헤더 Access Token")
    @Parameter(name = "게시글 ID", description = "게시글 ID")
    ResponseEntity<Void> create(String authorizationHeader, Long id);
    @Operation(summary = "좋아요 삭제", description = "게시글 좋아요 삭제")
    @Parameter(name = "Access Token", description = "Authorization 헤더 Access Token")
    @Parameter(name = "게시글 ID", description = "게시글 ID")
    ResponseEntity<Void> delete(String authorizationHeader, Long id);
}
