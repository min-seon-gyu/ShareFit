package com.example.ShareFit.domain.like.service;

import com.example.ShareFit.domain.like.Like;
import com.example.ShareFit.domain.like.dto.LikeResponseDto;
import com.example.ShareFit.domain.like.repository.LikeRepository;
import com.example.ShareFit.domain.member.Member;
import com.example.ShareFit.domain.member.repository.MemberRepository;
import com.example.ShareFit.domain.post.Post;
import com.example.ShareFit.domain.post.repository.PostRepository;
import com.example.ShareFit.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public LikeResponseDto addLike(String token, Long id) {
        Post post = postRepository.findWithLockById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 포스트가 존재하지 않습니다."));

        Member member = memberRepository.findById(jwtUtil.getId(token))
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 존재하지 않습니다."));

        Like like = Like.builder()
                .member(member)
                .post(post)
                .build();

        likeRepository.save(like);
        post.addLikes();

        return createLikeResponseDto(like);
    }

    @Transactional
    public void cancelLike(String token, Long id) {
        Post post = postRepository.findWithLockById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 포스트가 존재하지 않습니다."));

        Like like = likeRepository.findByMemberIdAndPostId(jwtUtil.getId(token), id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 좋아요가 존재하지 않습니다."));

        likeRepository.delete(like);
        post.cancelLikes();
    }

    private LikeResponseDto createLikeResponseDto(Like like){
        LikeResponseDto likeResponseDto = LikeResponseDto.builder()
                .id(like.getId())
                .build();

        return likeResponseDto;
    }
}
