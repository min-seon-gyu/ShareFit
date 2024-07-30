package com.example.ShareFit.domain.post.repository;

import com.example.ShareFit.domain.member.QMember;
import com.example.ShareFit.domain.post.Post;
import com.example.ShareFit.domain.post.QPost;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostQueryDslRepository{

    private final JPAQueryFactory queryFactory;
    QPost post = QPost.post;

    @Override
    public List<Post> findPopularPosts() {
        QMember member = QMember.member;
        LocalDateTime oneMonthAgo = LocalDateTime.now().minus(1, ChronoUnit.MONTHS);

        List<Post> fetch = queryFactory.selectFrom(post)
                .where(ageLocalDateTime(oneMonthAgo), ageLikeCount(0l))
                .join(post.member, member)
                .fetchJoin()
                .limit(100l)
                .fetch();

        return fetch;
    }

    private BooleanExpression ageLikeCount(Long likeCount) {
        return likeCount != null ? post.likeCount.goe(likeCount) : null;
    }

    private BooleanExpression ageLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime != null ? post.createdDate.goe(localDateTime) : null;
    }
}
