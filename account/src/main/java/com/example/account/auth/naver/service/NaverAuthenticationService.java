package com.example.account.auth.naver.service;

import com.example.account.auth.naver.service.response.NaverLoginResponse;

public interface NaverAuthenticationService {
    NaverLoginResponse handleLogin(String code, String state);
}
