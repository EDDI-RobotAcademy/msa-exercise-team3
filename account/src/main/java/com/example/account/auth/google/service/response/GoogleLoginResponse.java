package com.example.account.auth.google.service.response;

public abstract class GoogleLoginResponse {
    public abstract String getHtmlResponse();

    protected static String escape(String str) {
        return str.replace("'", "\\'");
    }
}
