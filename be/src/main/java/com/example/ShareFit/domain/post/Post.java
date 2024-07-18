package com.example.ShareFit.domain.post;

import com.example.ShareFit.common.JpaBaseEntity;
import com.example.ShareFit.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

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
}
