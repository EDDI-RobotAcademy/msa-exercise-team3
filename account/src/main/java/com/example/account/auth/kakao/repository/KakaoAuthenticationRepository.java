package com.example.account.auth.kakao.repository;

import java.util.Map;

public interface KakaoAuthenticationRepository {
    Map<String, Object> getAccessToken(String code);
    Map<String, Object> getUserInfo(String accessToken);
}
