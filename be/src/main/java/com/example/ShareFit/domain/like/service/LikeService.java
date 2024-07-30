package com.example.ShareFit.domain.like.service;

import com.example.ShareFit.domain.like.Like;
import com.example.ShareFit.domain.like.repository.LikeRepository;
import com.example.ShareFit.domain.member.Member;
import com.example.ShareFit.domain.member.repository.MemberRepository;
import com.example.ShareFit.domain.post.Post;
import com.example.ShareFit.domain.post.repository.PostRepository;
import com.example.ShareFit.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;
    private static final int MAX_RETRIES = 3;

    @Transactional
    @CacheEvict(value = "PostDetailResponseDto", key = "#id", cacheManager = "contentCacheManager")
    public void create(String token, Long id) {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                Post post = postRepository.findByIdWithOptimisticLock(id)
                        .orElseThrow(() -> new IllegalArgumentException("해당하는 포스트가 존재하지 않습니다."));

                Member member = memberRepository.findById(jwtUtil.getId(token))
                        .orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 존재하지 않습니다."));

                likeRepository.findByMemberIdAndPostId(jwtUtil.getId(token), id)
                        .ifPresent(l -> { throw new IllegalArgumentException("해당하는 좋아요가 이미 존재합니다."); });

                Like like = Like.builder()
                        .member(member)
                        .post(post)
                        .build();

                likeRepository.save(like);
                post.plusLike();
                break;
            } catch (OptimisticLockingFailureException e) {
                retries++;
                if (retries >= MAX_RETRIES) {
                    throw e;
                }
                try {
                    Thread.sleep(100); // 100ms 대기
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    @Transactional
    @CacheEvict(value = "PostDetailResponseDto", key = "#id", cacheManager = "contentCacheManager")
    public void delete(String token, Long id) {
        int retries = 0;
        while (retries < MAX_RETRIES) {
            try {
                Post post = postRepository.findByIdWithOptimisticLock(id)
                        .orElseThrow(() -> new IllegalArgumentException("해당하는 포스트가 존재하지 않습니다."));

                Like like = likeRepository.findByMemberIdAndPostId(jwtUtil.getId(token), id)
                        .orElseThrow(() -> new IllegalArgumentException("해당하는 좋아요가 존재하지 않습니다."));

                likeRepository.delete(like);
                post.minusLike();
                break;
            } catch (OptimisticLockingFailureException e) {
                retries++;
                if (retries >= MAX_RETRIES) {
                    throw e;
                }
                try {
                    Thread.sleep(100); // 100ms 대기
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
