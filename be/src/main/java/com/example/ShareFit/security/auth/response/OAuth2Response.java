package com.example.ShareFit.security.auth.response;

public interface OAuth2Response {
    String getProvider();
    String getProviderId();
    String getNickname();
    String getImagePath();
}
