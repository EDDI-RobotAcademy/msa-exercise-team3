package com.example.account.auth.naver.service.response;

public abstract class NaverLoginResponse {
    public abstract String getHtmlResponse();

    protected static String escape(String str) {
        return str.replace("'", "\\'");
    }
}
