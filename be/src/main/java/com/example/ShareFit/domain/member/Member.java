package com.example.ShareFit.domain.member;

import com.example.ShareFit.common.JpaBaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String profilePath;
    private String role;

    @Builder
    public Member(String uuid, String nickname, String role, String profilePath) {
        this.uuid = uuid;
        this.nickname = nickname;
        this.profilePath = profilePath;
        this.role = role;
    }

    public void update(String nickname){
        if(nickname != null && !nickname.isBlank()){
            this.nickname = nickname;
        }
    }
}
