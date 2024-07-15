package com.example.ShareFit.security.auth.controller;

import com.example.ShareFit.common.swagger.AuthControllerDocs;
import com.example.ShareFit.security.auth.dto.AuthRequestDto;
import com.example.ShareFit.security.auth.dto.AuthResponseDto;
import com.example.ShareFit.security.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController implements AuthControllerDocs {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(HttpServletResponse response,
                                                @RequestBody AuthRequestDto authRequestDto) {
        return authService.login(authRequestDto, response);
    }

    @GetMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refresh(HttpServletRequest request, HttpServletResponse response) {
        return authService.refresh(request, response);
    }
}
