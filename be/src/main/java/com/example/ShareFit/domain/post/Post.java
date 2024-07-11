package com.example.ShareFit.domain.post;

import com.example.ShareFit.common.JpaBaseEntity;
import com.example.ShareFit.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends JpaBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;
    private String title;
    private String content;
    private String path;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Post(String title, String content, String path, Member member) {
        this.title = title;
        this.content = content;
        this.path = path;
        this.member = member;
    }

    public void update(String title, String content, String path){
        if(title != null || !title.isBlank()){
            this.title = title;
        }

        if(content != null || !content.isBlank()){
            this.content = content;
        }

        if(path != null || !path.isBlank()){
            this.path = path;
        }
    }
}
