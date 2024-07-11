package com.example.ShareFit.domain.post.controller;

import com.example.ShareFit.common.swagger.PostControllerDocs;
import com.example.ShareFit.domain.post.dto.PostCreateDto;
import com.example.ShareFit.domain.post.dto.PostResponseDto;
import com.example.ShareFit.domain.post.dto.PostUpdateDto;
import com.example.ShareFit.domain.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController implements PostControllerDocs {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> save(HttpServletRequest request, @RequestBody PostCreateDto postCreateDto){
        String accessToken = request.getHeader("Authorization");
        PostResponseDto postResponseDto = postService.save(accessToken, postCreateDto);
        return ResponseEntity.ok(postResponseDto);
    }

    @GetMapping("/{post_id}")
    public ResponseEntity<PostResponseDto> find(@PathVariable("post_id") Long id){
        PostResponseDto postResponseDto = postService.find(id);
        return ResponseEntity.ok(postResponseDto);
    }

    @PatchMapping("/{post_id}")
    public ResponseEntity<PostResponseDto> update(@PathVariable("post_id") Long id,
                                                  @RequestBody PostUpdateDto postUpdateDto){
        PostResponseDto postResponseDto = postService.update(id, postUpdateDto);
        return ResponseEntity.ok(postResponseDto);
    }

    @DeleteMapping("/{post_id}")
    public ResponseEntity<Void> delete(@PathVariable("post_id") Long id){
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
