package com.example.ShareFit.domain.post.controller;

import com.example.ShareFit.common.S3Service;
import com.example.ShareFit.common.swagger.PostControllerDocs;
import com.example.ShareFit.domain.post.dto.*;
import com.example.ShareFit.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PostController implements PostControllerDocs {
    private final PostService postService;
    private final S3Service s3Service;

    @PostMapping("/post/image")
    public ResponseEntity<ImageResponseDto> uploadImage(@RequestPart(value = "image") MultipartFile image) throws IOException {
        String url = s3Service.upload(image);
        ImageResponseDto imageResponseDto = new ImageResponseDto(url);
        return ResponseEntity.ok(imageResponseDto);
    }

    @PostMapping("/post")
    public ResponseEntity<PostResponseDto> create(@RequestHeader("Authorization") String authorizationHeader,
                                                  @RequestBody PostCreateDto postCreateDto){
        String accessToken = authorizationHeader.split("\\s")[1];
        PostResponseDto postResponseDto = postService.create(accessToken, postCreateDto);
        return ResponseEntity.ok(postResponseDto);
    }

    @GetMapping("/posts/popular")
    public ResponseEntity<PostPopularResponseDto> findPopular(@RequestHeader("Authorization") String authorizationHeader){
        String accessToken = authorizationHeader.split("\\s")[1];
        PostPopularResponseDto postPopularResponseDto = postService.findPopular(accessToken);
        return ResponseEntity.ok(postPopularResponseDto);
    }

    @GetMapping("/post/{post_id}")
    public ResponseEntity<PostDetailResponseDto> find(@RequestHeader("Authorization") String authorizationHeader,
                                                      @PathVariable("post_id") Long id){
        String accessToken = authorizationHeader.split("\\s")[1];
        PostDetailResponseDto postDetailResponseDto = postService.find(accessToken, id);
        return ResponseEntity.ok(postDetailResponseDto);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostPageResponseDto> findAll(@RequestHeader("Authorization") String authorizationHeader,
                                                       @RequestParam(value = "uuid", required = false) String uuid,
                                                       @PageableDefault(size = 12, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable){
        String accessToken = authorizationHeader.split("\\s")[1];
        PostPageResponseDto postPageResponseDto = postService.findAll(accessToken, pageable, uuid);
        return ResponseEntity.ok(postPageResponseDto);
    }

    @PatchMapping("/post")
    public ResponseEntity<PostDetailResponseDto> update(@RequestHeader("Authorization") String authorizationHeader,
                                                        @RequestBody PostUpdateDto postUpdateDto){
        String accessToken = authorizationHeader.split("\\s")[1];
        PostDetailResponseDto postDetailResponseDto = postService.update(accessToken, postUpdateDto);
        return ResponseEntity.ok(postDetailResponseDto);
    }

    @DeleteMapping("/post/{post_id}")
    public ResponseEntity<Void> delete(@PathVariable("post_id") Long id){
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
