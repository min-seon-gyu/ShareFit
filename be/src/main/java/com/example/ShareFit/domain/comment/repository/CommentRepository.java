package com.example.ShareFit.domain.comment.repository;

import com.example.ShareFit.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
