package com.example.ShareFit.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class ShareFitOAuth2User implements OAuth2User {

    private final MemberSecurity memberSecurity;

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add((GrantedAuthority) () -> memberSecurity.getRole());
        return collection;
    }

    @Override
    public String getName() {
        return memberSecurity.getUuid();
    }

    public Long getId(){
        return memberSecurity.getId();
    }

    public String getNickname(){
        return memberSecurity.getNickname();
    }

    public String getImagePath(){
        return memberSecurity.getImagePath();
    }
    public String getRole(){
        return memberSecurity.getRole();
    }
}
