package com.example.ShareFit.domain.post.service;

import com.example.ShareFit.common.S3Service;
import com.example.ShareFit.domain.member.Member;
import com.example.ShareFit.domain.member.repository.MemberRepository;
import com.example.ShareFit.domain.post.Post;
import com.example.ShareFit.domain.post.dto.ImageResponseDto;
import com.example.ShareFit.domain.post.dto.PostCreateDto;
import com.example.ShareFit.domain.post.dto.PostResponseDto;
import com.example.ShareFit.domain.post.dto.PostUpdateDto;
import com.example.ShareFit.domain.post.repository.PostRepository;
import com.example.ShareFit.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public PostResponseDto save(String token, PostCreateDto postCreateDto) {
        Long memberId = jwtUtil.getId(token);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 존재하지 않습니다."));

        Post post = Post.builder()
                .content(postCreateDto.getContent())
                .imagePath(postCreateDto.getImagePath())
                .member(member)
                .build();

        postRepository.save(post);
        return createPostResponseDto(post);
    }

    @Transactional(readOnly = true)
    public PostResponseDto find(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 포스트가 존재하지 않습니다."));

        return createPostResponseDto(post);
    }

    @Transactional
    public PostResponseDto update(PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(postUpdateDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 포스트가 존재하지 않습니다."));

        post.update(postUpdateDto.getContent(), postUpdateDto.getImagePath());
        return createPostResponseDto(post);
    }

    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    private String saveImage(MultipartFile image){
        //이미지 S3 저장 로직
        return "";
    }
    private PostResponseDto createPostResponseDto(Post post){
        PostResponseDto postResponseDto = PostResponseDto.builder()
                .id(post.getId())
                .content(post.getContent())
                .imagePath(post.getImagePath())
                .build();

        return postResponseDto;
    }


}
