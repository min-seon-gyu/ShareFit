package com.example.ShareFit.domain.like.controller;

import com.example.ShareFit.common.swagger.LikeControllerDocs;
import com.example.ShareFit.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikeController implements LikeControllerDocs {
    private final LikeService likeService;

    @PostMapping("/like/{post_id}")
    public ResponseEntity<Void> create(@RequestHeader("Authorization") String authorizationHeader,
                                       @PathVariable(value = "post_id") Long id) {
        String accessToken = authorizationHeader.split("\\s")[1];
        likeService.create(accessToken, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/like/{post_id}")
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String authorizationHeader,
                                       @PathVariable(value = "post_id") Long id) {
        String accessToken = authorizationHeader.split("\\s")[1];
        likeService.delete(accessToken, id);
        return ResponseEntity.noContent().build();
    }
}
