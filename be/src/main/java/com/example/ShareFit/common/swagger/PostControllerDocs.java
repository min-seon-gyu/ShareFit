package com.example.ShareFit.common.swagger;

import com.example.ShareFit.domain.post.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Tag(name = "게시글 API")
public interface PostControllerDocs {

    @Operation(summary = "이미지 등록", description = "MultipartFile 활용하여 이미지 등록")
    @Parameter(name = "이미지", description = "등록 이미지")
    public ResponseEntity<ImageResponseDto> uploadImage(MultipartFile image) throws IOException;

    @Operation(summary = "게시글 등록", description = "Access Token, PostCreateDto 활용하여 게시글 등록")
    @Parameter(name = "Access Token", description = "Authorization 헤더 Access Token")
    @Parameter(name = "PostCreateDto", description = "게시글 등록 내용")
    public ResponseEntity<PostResponseDto> save(String authorizationHeader, PostCreateDto postCreateDto);

    @Operation(summary = "게시글 상세 조회", description = "게시글 ID 활용하여 게시글 상세 조회")
    @Parameter(name = "게시글 ID", description = "게시글 ID")
    ResponseEntity<PostDetailResponseDto> findDetail(Long id);

    @Operation(summary = "게시글 페이지 조회", description = "회원 UUID, 페이징 조건 활용하여 게시글 페이지 조회 " +
            "ex) /pages?uuid=value&page=value or /pages?page=value " +
            "(uuid 필터링 시 uuid 먼저, page 0부터 시작)")
    @Parameter(name = "회원 UUID", description = "회원 UUID, 필수 X")
    @Parameter(name = "Pageable", description = "페이징 조건")
    ResponseEntity<PostPageResponseDto> findAll(String uuid, Pageable pageable);

    @Operation(summary = "게시글 업데이트", description = "PostUpdateDto 활용하여 게시글 업데이트")
    @Parameter(name = "PostUpdateDto", description = "게시글 수정 내용")
    ResponseEntity<PostResponseDto> update(Long id, PostUpdateDto postUpdateDto);

    @Operation(summary = "게시글 삭제", description = "게시글 ID 활용하여 게시글 삭제")
    @Parameter(name = "게시글 ID", description = "게시글 ID")
    ResponseEntity<Void> delete(Long id);
}
