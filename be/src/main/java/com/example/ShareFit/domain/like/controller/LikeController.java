package com.example.ShareFit.domain.like.controller;

import com.example.ShareFit.common.swagger.LikeControllerDocs;
import com.example.ShareFit.domain.like.dto.LikeResponseDto;
import com.example.ShareFit.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikeController implements LikeControllerDocs {
    private final LikeService likeService;

    @PostMapping("/like/{post_id}")
    public ResponseEntity<LikeResponseDto> addLike(@RequestHeader("Authorization") String authorizationHeader,
                                                   @PathVariable(value = "post_id") Long id) {
        String accessToken = authorizationHeader.split("\\s")[1];
        LikeResponseDto likeResponseDto = likeService.addLike(accessToken, id);
        return ResponseEntity.ok(likeResponseDto);
    }

    @DeleteMapping("/like/{post_id}")
    public ResponseEntity<Void> cancelLike(@RequestHeader("Authorization") String authorizationHeader,
                                           @PathVariable(value = "post_id") Long id) {
        String accessToken = authorizationHeader.split("\\s")[1];
        likeService.cancelLike(accessToken, id);
        return ResponseEntity.noContent().build();
    }
}
