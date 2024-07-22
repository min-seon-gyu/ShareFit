package com.example.ShareFit.domain.like.repository;

import com.example.ShareFit.domain.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query("select l from Like l join fetch l.member m join fetch l.post p where m.id = :memberId and p.id = :postId")
    Optional<Like> findByMemberIdAndPostId(@Param("memberId") Long memberId, @Param("postId") Long postId);
}
