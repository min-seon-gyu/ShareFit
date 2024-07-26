package com.example.ShareFit.domain.member.service;

import com.example.ShareFit.domain.member.Member;
import com.example.ShareFit.domain.member.dto.MemberResponseDto;
import com.example.ShareFit.domain.member.dto.MemberUpdateDto;
import com.example.ShareFit.domain.member.repository.MemberRepository;
import com.example.ShareFit.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public MemberResponseDto create(String uuid, String nickname, String imagePath){
        Member member = Member.builder()
                .uuid(uuid)
                .nickname(nickname)
                .profilePath(imagePath)
                .role("USER")
                .build();

        memberRepository.save(member);
        return createMemberResponseDto(member);
    }

    @Transactional(readOnly = true)
    public MemberResponseDto find(String token){
        Long id = jwtUtil.getId(token);
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 존재하지 않습니다."));

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
        Optional<Member> findMember = memberRepository.findByUuid(uuid);
        if(findMember.isEmpty()) return null;

        return createMemberResponseDto(findMember.get());
    }

    @Transactional
    public MemberResponseDto update(String token, MemberUpdateDto memberUpdateDto) {
        Long id = jwtUtil.getId(token);
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 존재하지 않습니다."));

        member.update(memberUpdateDto.getNickname());
        return createMemberResponseDto(member);
    }

    @Transactional
    public void delete(String token) {
        Long id = jwtUtil.getId(token);
        memberRepository.deleteById(id);
    }

    private MemberResponseDto createMemberResponseDto(Member member){
        MemberResponseDto memberResponseDto = MemberResponseDto.builder()
                .id(member.getId())
                .uuid(member.getUuid())
                .nickname(member.getNickname())
                .profilePath(member.getProfilePath())
                .role(member.getRole())
                .build();

        return memberResponseDto;
    }
}
