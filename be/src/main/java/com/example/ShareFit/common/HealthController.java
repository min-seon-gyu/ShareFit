package com.example.ShareFit.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<Void> health(){
        System.out.println("health 요청이 왔습니다.");
        return ResponseEntity.noContent().build();
    }
}
