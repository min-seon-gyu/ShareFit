package com.example.ShareFit.common.swagger;

import com.example.ShareFit.security.auth.dto.AuthRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "인증 API")
public interface AuthControllerDocs {
    @Operation(summary = "로그인", description = "로그인 성공 시 어세스 토큰, 리프레시 토큰 발급")
    ResponseEntity<String> login(HttpServletResponse response,
                                        @RequestBody AuthRequestDto authRequestDto);

    @Operation(summary = "토큰 재발급", description = "리프레시 토큰을 활용하여 어세스 토큰, 리프레시 토큰 재발급")
    ResponseEntity<String> refresh(HttpServletRequest request, HttpServletResponse response);
}
