package com.example.ShareFit.domain.comment.repository;

import com.example.ShareFit.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c join fetch c.member where c.post.id = :postId")
    List<Comment> findByPostId(@Param("postId") Long postId);
    @Query("select c from Comment c join fetch c.member where c.id = :commentId")
    Optional<Comment> findByCommentId(@Param("commentId") Long commentId);
}
