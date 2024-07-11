package com.example.ShareFit.domain.member.controller;

import com.example.ShareFit.common.swagger.MemberControllerDocs;
import com.example.ShareFit.domain.member.dto.MemberResponseDto;
import com.example.ShareFit.domain.member.dto.MemberUpdateDto;
import com.example.ShareFit.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController implements MemberControllerDocs {
    private final MemberService memberService;

    @GetMapping("/{member_id}")
    public ResponseEntity<MemberResponseDto> find(@PathVariable("member_id") Long id){
        MemberResponseDto memberResponseDto = memberService.findById(id);
        return ResponseEntity.ok(memberResponseDto);
    }

    @PatchMapping("/{member_id}")
    public ResponseEntity<MemberResponseDto> update(@PathVariable("member_id") Long id,
                                                    @RequestBody MemberUpdateDto memberUpdateDto){
        MemberResponseDto memberResponseDto = memberService.update(id, memberUpdateDto);
        return ResponseEntity.ok(memberResponseDto);
    }

    @DeleteMapping("/{member_id}")
    public ResponseEntity<Void> delete(@PathVariable("member_id") Long id){
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
