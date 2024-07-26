package com.example.ShareFit.common.swagger;

import com.example.ShareFit.domain.member.dto.MemberResponseDto;
import com.example.ShareFit.domain.member.dto.MemberUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "회원 API")
public interface MemberControllerDocs {
    @Operation(summary = "회원 조회 토큰 방식", description = "Access Token 활용하여 회원 조회")
    @Parameter(name = "Access Token", description = "Authorization 헤더 Access Token")
    ResponseEntity<MemberResponseDto> find(String authorizationHeader);
    @Operation(summary = "회원 조회 ID 방식", description = "회원 ID 활용하여 회원 조회")
    @Parameter(name = "회원 ID", description = "회원 ID")
    ResponseEntity<MemberResponseDto> findById(Long id);
    @Operation(summary = "회원 수정", description = "Access Token, MemberUpdateDto 활용하여 회원 수정")
    @Parameter(name = "Access Token", description = "Authorization 헤더 Access Token")
    @Parameter(name = "MemberUpdateDto", description = "회원 수정 내용")
    ResponseEntity<MemberResponseDto> update(String authorizationHeader, MemberUpdateDto memberUpdateDto);

    @Operation(summary = "회원 탈퇴", description = "Access Token 활용하여 회원 탈퇴")
    @Parameter(name = "Access Token", description = "Authorization 헤더 Access Token")
    ResponseEntity<Void> delete(String authorizationHeader);
}
