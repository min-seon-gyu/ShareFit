package com.example.ShareFit.security.auth.response;

import java.util.Map;

public class KakaoResponse implements OAuth2Response{
    private final Map<String, Object> attributes;
    private final Map<String, Object> properties;

    public KakaoResponse(Map<String, Object> attributes) {
        this.attributes = attributes;
        properties = (Map<String, Object>) attributes.get("properties");
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getNickname() {
        return properties.get("nickname").toString();
    }

    @Override
    public String getImagePath() {
        return properties.get("profile_image").toString();
    }
}
