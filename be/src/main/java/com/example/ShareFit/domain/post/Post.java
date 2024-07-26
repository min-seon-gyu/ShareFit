package com.example.ShareFit.domain.post;

import com.example.ShareFit.common.JpaBaseEntity;
import com.example.ShareFit.domain.comment.Comment;
import com.example.ShareFit.domain.like.Like;
import com.example.ShareFit.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends JpaBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    private String content;
    private String imagePath;
    private Long totalLikeCount = 0l;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    @Builder
    public Post(String content, String imagePath, Member member) {
        this.content = content;
        this.imagePath = imagePath;
        this.member = member;
    }

    public void update(String content, String imagePath){
        if(content != null && !content.isBlank()){
            this.content = content;
        }

        if(imagePath != null && !imagePath.isBlank()){
            this.imagePath = imagePath;
        }
    }

    public void addLikes(){
        totalLikeCount++;
    }

    public void cancelLikes(){
        totalLikeCount--;
    }
}
