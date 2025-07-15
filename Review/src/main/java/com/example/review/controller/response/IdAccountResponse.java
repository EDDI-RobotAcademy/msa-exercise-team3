package com.example.review.controller.response;

public class IdAccountResponse {

    private Long accountId;
    private String userId;

    public IdAccountResponse() {
    }

    public IdAccountResponse(Long accountId, String userId) {
        this.accountId = accountId;
        this.userId = userId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getUserId() {
        return userId;
    }

}
