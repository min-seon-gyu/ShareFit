package com.example.ShareFit.domain.post.service;

import com.example.ShareFit.domain.member.Member;
import com.example.ShareFit.domain.member.repository.MemberRepository;
import com.example.ShareFit.domain.post.Post;
import com.example.ShareFit.domain.post.dto.PostCreateDto;
import com.example.ShareFit.domain.post.dto.PostPageResponseDto;
import com.example.ShareFit.domain.post.dto.PostResponseDto;
import com.example.ShareFit.domain.post.dto.PostUpdateDto;
import com.example.ShareFit.domain.post.repository.PostRepository;
import com.example.ShareFit.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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

    @Transactional(readOnly = true)
    public PostPageResponseDto findAll(Pageable pageable, String uuid) {
        Page<Post> result = null;

        System.out.println("uuid = " + uuid);

        if(uuid == null || uuid.isBlank()){
            result = postRepository.findAll(pageable);
        }else{
            result = postRepository.findAllByMemberUuid(uuid, pageable);
        }

        return createPostPageResponseDto(result);
    }

    @Transactional
    public PostResponseDto update(Long id, PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 포스트가 존재하지 않습니다."));

        post.update(postUpdateDto.getContent(), postUpdateDto.getImagePath());
        return createPostResponseDto(post);
    }

    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    private PostResponseDto createPostResponseDto(Post post){
        PostResponseDto postResponseDto = PostResponseDto.builder()
                .id(post.getId())
                .content(post.getContent())
                .imagePath(post.getImagePath())
                .nickname(post.getMember().getNickname())
                .profilePath(post.getMember().getProfilePath())
                .build();

        return postResponseDto;
    }

    private PostPageResponseDto createPostPageResponseDto(Page<Post> posts){
        List<PostResponseDto> convert = posts.stream().map(p -> createPostResponseDto(p)).toList();
        PostPageResponseDto postPageResponseDto = PostPageResponseDto.builder()
                .count(convert.size())
                .result(convert)
                .build();

        return postPageResponseDto;
    }
}
