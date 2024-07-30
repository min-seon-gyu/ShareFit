package com.example.ShareFit.domain.post.repository;

import com.example.ShareFit.domain.post.Post;
import java.util.List;

public interface PostQueryDslRepository {
    List<Post> findPopularPosts();
}
