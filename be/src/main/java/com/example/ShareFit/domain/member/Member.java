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

    @Builder
    public Member(String uuid, String nickname, String role) {
        this.uuid = uuid;
        this.nickname = nickname;
        this.role = role;
    }

    public void update(String nickname){
        if(nickname != null && !nickname.isBlank()){
            this.nickname = nickname;
        }
    }
}
