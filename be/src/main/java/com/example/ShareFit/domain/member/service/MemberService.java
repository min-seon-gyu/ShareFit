package com.example.ShareFit.domain.member.service;

import com.example.ShareFit.domain.member.Member;
import com.example.ShareFit.domain.member.dto.MemberResponseDto;
import com.example.ShareFit.domain.member.dto.MemberUpdateDto;
import com.example.ShareFit.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto save(String uuid){
        Member member = Member.builder()
                .uuid(uuid)
                .role("USER")
                .build();

        memberRepository.save(member);
        return createMemberResponseDto(member);
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 존재하지 않습니다."));

        return createMemberResponseDto(member);
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findByUuid(String uuid){
        Member member = memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 존재하지 않습니다."));

        return createMemberResponseDto(member);
    }

    @Transactional
    public MemberResponseDto update(Long id, MemberUpdateDto memberUpdateDto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 존재하지 않습니다."));

        member.update(memberUpdateDto.getNickname());
        return createMemberResponseDto(member);
    }

    @Transactional
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    private MemberResponseDto createMemberResponseDto(Member member){
        MemberResponseDto memberResponseDto = MemberResponseDto.builder()
                .uuid(member.getUuid())
                .nickname(member.getNickname())
                .role(member.getRole())
                .build();

        return memberResponseDto;
    }
}
