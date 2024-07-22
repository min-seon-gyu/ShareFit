package com.example.ShareFit.domain.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "게시글 페이지 조회 응답 DTO")
public class PostPageResponseDto {
    @Schema(description = "전체 게시글 수")
    private int count;
    @Schema(description = "게시글 리스트")
    private List<PostResponseDto> result = new ArrayList<>();
}
