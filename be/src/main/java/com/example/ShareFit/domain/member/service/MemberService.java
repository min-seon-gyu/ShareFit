package com.example.ShareFit.domain.member.service;

import com.example.ShareFit.domain.member.Member;
import com.example.ShareFit.domain.member.dto.MemberResponseDto;
import com.example.ShareFit.domain.member.dto.MemberUpdateDto;
import com.example.ShareFit.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 존재하지 않습니다."));

        return createMemberResponseDto(member);
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findByUuid(String uuid){
        Optional<Member> findMember = memberRepository.findByUuid(uuid);
        if(findMember.isEmpty()) return null;

        return createMemberResponseDto(findMember.get());
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
                .id(member.getId())
                .uuid(member.getUuid())
                .nickname(member.getNickname())
                .imagePath(member.getImagePath())
                .role(member.getRole())
                .build();

        return memberResponseDto;
    }
}
