package com.example.ShareFit.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSecurity {
    private Long id;
    private String uuid;
    private String nickname;
    private String imagePath;
    private String role;
}
