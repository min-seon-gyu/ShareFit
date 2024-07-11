package com.example.ShareFit.domain.member.repository;

import com.example.ShareFit.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUuid(String uuid);
}
