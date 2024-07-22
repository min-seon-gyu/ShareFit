package com.example.ShareFit.domain.post.repository;

import com.example.ShareFit.domain.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"member"})
    Page<Post> findAll(Pageable pageable);

    @Query("select p from Post p join fetch p.member m where m.uuid = :memberUuid")
    Page<Post> findAllByMemberUuid(@Param("memberUuid") String memberUuid, Pageable pageable);
}
