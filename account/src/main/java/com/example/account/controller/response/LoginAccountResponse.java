package com.example.account.controller.response;

public class LoginAccountResponse {
    private String userToken;
    private String message;

    public LoginAccountResponse() {
    }

    public LoginAccountResponse(String message) {
        this.message = message;
    }

    public static LoginAccountResponse from(String message, String userToken) {
        return new LoginAccountResponse(message, userToken);
    }

    public LoginAccountResponse(String userToken, String message) {
        this.userToken = userToken;
        this.message = message;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
