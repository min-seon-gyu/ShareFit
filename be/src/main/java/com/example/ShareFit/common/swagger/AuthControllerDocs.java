package com.example.ShareFit.common.swagger;

import com.example.ShareFit.security.auth.dto.AuthResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

@Tag(name = "인증 API")
public interface AuthControllerDocs {
    @Operation(summary = "토큰 재발급", description = "리프레시 토큰을 활용하여 어세스 토큰, 리프레시 토큰 재발급")
    ResponseEntity<AuthResponseDto> refresh(HttpServletRequest request, HttpServletResponse response);
}
