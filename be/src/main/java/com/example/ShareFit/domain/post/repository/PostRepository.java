package com.example.ShareFit.domain.post.repository;

import com.example.ShareFit.domain.post.Post;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Post> findWithLockById(Long id);
    @Query("select p from Post p join fetch p.member")
    Page<Post> findAllJPA(Pageable pageable);
    @Query("select p from Post p join fetch p.member m where m.uuid = :memberUuid")
    Page<Post> findAllByMemberUuidJPA(@Param("memberUuid") String memberUuid, Pageable pageable);
    @Query("select p from Post p join fetch p.member left join fetch p.likes where p.id = :postId")
    Optional<Post> findDetailById(@Param("postId") Long postId);
}
