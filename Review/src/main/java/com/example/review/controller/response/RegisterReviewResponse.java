package com.example.review.controller.response;

public class RegisterReviewResponse {
    private Long id;
    private String userId;
    private String nickName;
    private String reviewTitle;
    private String reviewContent;

    public RegisterReviewResponse() {

    }

    public RegisterReviewResponse(String userId, String nickName, String reviewTitle, String reviewContent) {

        this.userId = userId;
        this.nickName = nickName;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }
}

