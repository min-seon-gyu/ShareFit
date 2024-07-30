package com.example.ShareFit.domain.post.service;

import com.example.ShareFit.domain.member.Member;
import com.example.ShareFit.domain.member.repository.MemberRepository;
import com.example.ShareFit.domain.post.Post;
import com.example.ShareFit.domain.post.dto.*;
import com.example.ShareFit.domain.post.repository.PostRepository;
import com.example.ShareFit.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

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
    public PostPopularResponseDto findPopular(String token) {
        Long memberId = jwtUtil.getId(token);
        List<Post> posts = postRepository.findPopularPosts().stream()
                .sorted(Comparator.comparingLong(this::calculateScore).reversed())
                .limit(3)
                .toList();

        return new PostPopularResponseDto(posts, memberId);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "PostDetailResponseDto", key = "#id", cacheManager = "contentCacheManager")
    public PostDetailResponseDto find(String token, Long id) {
        Long memberId = jwtUtil.getId(token);
        Post post = postRepository.findById(id)
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
    @CachePut(value = "PostDetailResponseDto", key = "#postUpdateDto.getId()", cacheManager = "contentCacheManager")
    public PostDetailResponseDto update(String token, PostUpdateDto postUpdateDto) {
        Long memberId = jwtUtil.getId(token);
        Post post = postRepository.findById(postUpdateDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 포스트가 존재하지 않습니다."));

        post.update(postUpdateDto.getContent(), postUpdateDto.getImagePath());
        return new PostDetailResponseDto(post, memberId);
    }

    @Transactional
    @CacheEvict(value = "PostDetailResponseDto", key = "#id", cacheManager = "contentCacheManager")
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    private int calculateScore(Post post) {
        int likeCountScore = Long.valueOf(post.getLikeCount()).intValue() / 10;
        int commentCountScore = post.getComments().size();
        int createDateScore = (int) (LocalDateTime.now().toLocalDate().toEpochDay() - post.getCreatedDate().toLocalDate().toEpochDay() * 2);
        return likeCountScore + commentCountScore - createDateScore;
    }
}
