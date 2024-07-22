package com.example.ShareFit.domain.post.repository;

import com.example.ShareFit.domain.post.Post;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Post> findWithLockById(Long id);

    @EntityGraph(attributePaths = {"member"})
    Page<Post> findAll(Pageable pageable);

    @Query("select p from Post p join fetch p.member m where m.uuid = :memberUuid")
    Page<Post> findAllByMemberUuid(@Param("memberUuid") String memberUuid, Pageable pageable);
}
