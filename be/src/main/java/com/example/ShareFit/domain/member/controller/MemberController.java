package com.example.ShareFit.domain.member.controller;

import com.example.ShareFit.common.swagger.MemberControllerDocs;
import com.example.ShareFit.domain.member.dto.MemberResponseDto;
import com.example.ShareFit.domain.member.dto.MemberUpdateDto;
import com.example.ShareFit.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController implements MemberControllerDocs {
    private final MemberService memberService;

    @GetMapping("/member")
    public ResponseEntity<MemberResponseDto> find(@RequestHeader("Authorization") String authorizationHeader){
        String accessToken = authorizationHeader.split("\\s")[1];
        MemberResponseDto memberResponseDto = memberService.find(accessToken);
        return ResponseEntity.ok(memberResponseDto);
    }

    @GetMapping("/member/{member_id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable("member_id") Long id){
        MemberResponseDto memberResponseDto = memberService.findById(id);
        return ResponseEntity.ok(memberResponseDto);
    }

    @PatchMapping("/member")
    public ResponseEntity<MemberResponseDto> update(@RequestHeader("Authorization") String authorizationHeader,
                                                    @RequestBody MemberUpdateDto memberUpdateDto){
        String accessToken = authorizationHeader.split("\\s")[1];
        MemberResponseDto memberResponseDto = memberService.update(accessToken, memberUpdateDto);
        return ResponseEntity.ok(memberResponseDto);
    }

    @DeleteMapping("/member")
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String authorizationHeader){
        String accessToken = authorizationHeader.split("\\s")[1];
        memberService.delete(accessToken);
        return ResponseEntity.noContent().build();
    }
}
