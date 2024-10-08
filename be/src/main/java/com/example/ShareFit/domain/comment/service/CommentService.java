package com.example.ShareFit.domain.comment.service;

import com.example.ShareFit.domain.comment.Comment;
import com.example.ShareFit.domain.comment.dto.CommentCreateDto;
import com.example.ShareFit.domain.comment.dto.CommentResponseDto;
import com.example.ShareFit.domain.comment.dto.CommentUpdateDto;
import com.example.ShareFit.domain.comment.repository.CommentRepository;
import com.example.ShareFit.domain.member.Member;
import com.example.ShareFit.domain.member.repository.MemberRepository;
import com.example.ShareFit.domain.post.Post;
import com.example.ShareFit.domain.post.repository.PostRepository;
import com.example.ShareFit.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    @CacheEvict(value = "PostDetailResponseDto", key = "#commentCreateDto.getId()", cacheManager = "contentCacheManager")
    public CommentResponseDto create(String token, CommentCreateDto commentCreateDto) {
        Long memberId = jwtUtil.getId(token);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 존재하지 않습니다."));

        Post post = postRepository.findById(commentCreateDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 포스트가 존재하지 않습니다."));

        Comment comment = Comment.builder()
                .content(commentCreateDto.getContent())
                .member(member)
                .post(post)
                .build();

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Transactional
    @CacheEvict(value = "PostDetailResponseDto", key = "#commentUpdateDto.getPostId()", cacheManager = "contentCacheManager")
    public CommentResponseDto update(CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.findByCommentId(commentUpdateDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 댓글이 존재하지 않습니다."));

        comment.update(commentUpdateDto.getContent());
        return new CommentResponseDto(comment);
    }

    @Transactional
    @CacheEvict(value = "PostDetailResponseDto", key = "#postId", cacheManager = "contentCacheManager")
    public void delete(Long postId, Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
