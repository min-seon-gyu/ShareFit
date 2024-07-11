package com.example.ShareFit.domain.member;

import com.example.ShareFit.common.JpaBaseEntity;
import com.example.ShareFit.domain.post.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends JpaBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String uuid;
    private String nickname;
    private String role;
    private boolean available;
    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String uuid, String role) {
        this.uuid = uuid;
        this.role = role;
    }

    public void addPost(Post post){
        this.posts.add(post);
        post.setMember(this);
    }

    public void update(String nickname){
        if(nickname != null || !nickname.isBlank()){
            this.nickname = nickname;
        }

        if(!available) available = true;
    }
}
