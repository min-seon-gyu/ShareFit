package com.example.ShareFit.domain.post.controller;

import com.example.ShareFit.common.S3Service;
import com.example.ShareFit.common.swagger.PostControllerDocs;
import com.example.ShareFit.domain.post.dto.ImageResponseDto;
import com.example.ShareFit.domain.post.dto.PostCreateDto;
import com.example.ShareFit.domain.post.dto.PostResponseDto;
import com.example.ShareFit.domain.post.dto.PostUpdateDto;
import com.example.ShareFit.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController implements PostControllerDocs {
    private final PostService postService;
    private final S3Service s3Service;

    @PostMapping("/image")
    public ResponseEntity<ImageResponseDto> uploadImage(@RequestPart(value = "image") MultipartFile image) throws IOException {
        String url = s3Service.upload(image);
        ImageResponseDto imageResponseDto = new ImageResponseDto(url);
        return ResponseEntity.ok(imageResponseDto);
    }

    @PostMapping
    public ResponseEntity<PostResponseDto> save(@RequestHeader("Authorization") String authorizationHeader,
                                                @RequestBody PostCreateDto postCreateDto){
        String accessToken = authorizationHeader.split("\\s")[1];
        PostResponseDto postResponseDto = postService.save(accessToken, postCreateDto);
        return ResponseEntity.ok(postResponseDto);
    }

    @GetMapping("/{post_id}")
    public ResponseEntity<PostResponseDto> find(@PathVariable("post_id") Long id){
        PostResponseDto postResponseDto = postService.find(id);
        return ResponseEntity.ok(postResponseDto);
    }

    @PatchMapping
    public ResponseEntity<PostResponseDto> update(@RequestBody PostUpdateDto postUpdateDto){
        PostResponseDto postResponseDto = postService.update(postUpdateDto);
        return ResponseEntity.ok(postResponseDto);
    }

    @DeleteMapping("/{post_id}")
    public ResponseEntity<Void> delete(@PathVariable("post_id") Long id){
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
