package com.example.ShareFit.security.auth.controller;

import com.example.ShareFit.common.swagger.AuthControllerDocs;
import com.example.ShareFit.security.auth.dto.AuthResponseDto;
import com.example.ShareFit.security.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController implements AuthControllerDocs {
    private final AuthService authService;

    @GetMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refresh(HttpServletRequest request, HttpServletResponse response) {
        AuthResponseDto authResponseDto = authService.refresh(request, response);
        return ResponseEntity.ok(authResponseDto);
    }
}
