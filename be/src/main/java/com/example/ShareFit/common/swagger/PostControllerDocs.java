package com.example.ShareFit.common.swagger;

import com.example.ShareFit.domain.post.dto.PostCreateDto;
import com.example.ShareFit.domain.post.dto.PostResponseDto;
import com.example.ShareFit.domain.post.dto.PostUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "게시글 API")
public interface PostControllerDocs {

    @Operation(summary = "이미지 등록", description = "파라미터로 받은 데이터를 활용하여 이미지 등록")
    public ResponseEntity<String> uploadImage(@RequestPart(value = "image") MultipartFile image) throws IOException;

    @Operation(summary = "게시글 등록", description = "파라미터로 받은 데이터를 활용하여 게시글 등록")
    public ResponseEntity<PostResponseDto> save(@RequestHeader("Authorization") String authorizationHeader,
                                                @RequestBody PostCreateDto postCreateDto);

    @Operation(summary = "게시글 조회", description = "파라미터로 받은 데이터를 활용하여 게시글 단일 조회")
    ResponseEntity<PostResponseDto> find(@PathVariable("post_id") Long id);

    @Operation(summary = "게시글 업데이트", description = "파라미터로 받은 데이터를 활용하여 게시글 업데이트")
    ResponseEntity<PostResponseDto> update(@RequestBody PostUpdateDto postUpdateDto);

    @Operation(summary = "게시글 삭제", description = "파라미터로 받은 데이터를 활용하여 게시글 삭제")
    ResponseEntity<Void> delete(@PathVariable("post_id") Long id);
}
