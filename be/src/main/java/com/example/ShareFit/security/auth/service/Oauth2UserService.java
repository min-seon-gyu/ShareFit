package com.example.ShareFit.security.auth.service;

import com.example.ShareFit.domain.member.Member;
import com.example.ShareFit.domain.member.repository.MemberRepository;
import com.example.ShareFit.security.MemberSecurity;
import com.example.ShareFit.security.ShareFitOAuth2User;
import com.example.ShareFit.security.auth.response.KakaoResponse;
import com.example.ShareFit.security.auth.response.OAuth2Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Oauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response = null;
        if(registrationId.equals("kakao")){
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }

        String uuid = oAuth2Response.getProvider() + "_" + oAuth2Response.getProviderId();
        String nickname = oAuth2Response.getNickname();
        String imagePath = oAuth2Response.getImagePath();

        Member member = memberRepository.findByUuid(uuid).orElseGet(() -> save(uuid, nickname, imagePath));

        MemberSecurity memberSecurity = MemberSecurity.builder()
                .id(member.getId())
                .uuid(member.getUuid())
                .nickname(member.getNickname())
                .imagePath(member.getImagePath())
                .role(member.getRole())
                .build();

        return new ShareFitOAuth2User(memberSecurity);
    }

    private Member save(String uuid, String nickname, String imagePath) {
        Member member = Member.builder()
                .uuid(uuid)
                .nickname(nickname)
                .imagePath(imagePath)
                .role("USER")
                .build();

        memberRepository.save(member);
        return member;
    }
}
