package com.example.account.auth.google.service;

import com.example.account.auth.google.service.response.GoogleLoginResponse;

public interface GoogleAuthenticationService {
    GoogleLoginResponse handleLogin(String code);
}
