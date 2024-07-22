package com.example.ShareFit.domain.like.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "좋아요 응답 DTO")
public class LikeResponseDto {
    @Schema(description = "좋아요 ID")
    private Long id;
}
