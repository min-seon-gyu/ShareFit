package com.example.ShareFit.domain.post.service;

import com.example.ShareFit.domain.member.Member;
import com.example.ShareFit.domain.member.repository.MemberRepository;
import com.example.ShareFit.domain.post.Post;
import com.example.ShareFit.domain.post.dto.*;
import com.example.ShareFit.domain.post.repository.PostRepository;
import com.example.ShareFit.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public PostResponseDto create(String token, PostCreateDto postCreateDto) {
        Long memberId = jwtUtil.getId(token);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 존재하지 않습니다."));

        Post post = Post.builder()
                .content(postCreateDto.getContent())
                .imagePath(postCreateDto.getImagePath())
                .member(member)
                .build();

        postRepository.save(post);
        return new PostResponseDto(post, memberId);
    }

    @Transactional(readOnly = true)
    public PostDetailResponseDto findDetail(String token, Long id) {
        Long memberId = jwtUtil.getId(token);
        Post post = postRepository.findDetailById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 포스트가 존재하지 않습니다."));

        return new PostDetailResponseDto(post, memberId);
    }

    @Transactional(readOnly = true)
    public PostPageResponseDto findAll(String token, Pageable pageable, String uuid) {
        Long memberId = jwtUtil.getId(token);
        if(uuid == null || uuid.isBlank()){
            return new PostPageResponseDto(postRepository.findAllJPA(pageable), memberId);
        }else{
            return new PostPageResponseDto(postRepository.findAllByMemberUuidJPA(uuid, pageable), memberId);
        }
    }

    @Transactional
    public PostDetailResponseDto update(String token, PostUpdateDto postUpdateDto) {
        Long memberId = jwtUtil.getId(token);
        Post post = postRepository.findById(postUpdateDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 포스트가 존재하지 않습니다."));

        post.update(postUpdateDto.getContent(), postUpdateDto.getImagePath());
        return new PostDetailResponseDto(post, memberId);
    }

    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
