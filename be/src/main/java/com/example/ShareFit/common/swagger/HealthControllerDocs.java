package com.example.ShareFit.common.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "상태체크 API")
public interface HealthControllerDocs {

    @Operation(summary = "상태 체크", description = "로드밸런서 타겟 상태 체크")
    ResponseEntity<Void> health();
}
