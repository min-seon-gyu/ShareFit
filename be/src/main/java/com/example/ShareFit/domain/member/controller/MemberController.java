package com.example.ShareFit.domain.member.controller;

import com.example.ShareFit.domain.member.dto.MemberResponseDto;
import com.example.ShareFit.domain.member.dto.MemberUpdateDto;
import com.example.ShareFit.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Tag(name = "회원 API")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{member_id}")
    @Operation(summary = "회원 조회", description = "파라미터로 받은 데이터를 활용하여 회원 조회")
    public ResponseEntity<MemberResponseDto> find(@PathVariable("member_id") Long id){
        MemberResponseDto memberResponseDto = memberService.findById(id);
        return ResponseEntity.ok(memberResponseDto);
    }

    @PatchMapping("/{member_id}")
    @Operation(summary = "회원 수정", description = "파라미터로 받은 데이터를 활용하여 회원 수정")
    public ResponseEntity<MemberResponseDto> update(@PathVariable("member_id") Long id,
                                                    MemberUpdateDto memberUpdateDto){
        MemberResponseDto memberResponseDto = memberService.update(id, memberUpdateDto);
        return ResponseEntity.ok(memberResponseDto);
    }

    @DeleteMapping("/{member_id}")
    @Operation(summary = "회원 탈퇴", description = "파라미터로 받은 데이터를 활용하여 회원 탈퇴")
    public ResponseEntity<Void> delete(@PathVariable("member_id") Long id){
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
