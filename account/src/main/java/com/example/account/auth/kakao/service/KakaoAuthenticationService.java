package com.example.account.auth.kakao.service;

import com.example.account.auth.kakao.service.response.KakaoLoginResponse;

public interface KakaoAuthenticationService {
    KakaoLoginResponse handleLogin(String code);
}
