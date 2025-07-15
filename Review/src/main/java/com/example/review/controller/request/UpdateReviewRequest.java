package com.example.review.controller.request;

import lombok.ToString;

@ToString
public class UpdateReviewRequest {

    Long reviewId;
    String nickname;
    String reviewTitle;
    String reviewContent;

    public UpdateReviewRequest(Long reviewId, String nickname, String reviewTitle, String reviewContent) {
        this.reviewId = reviewId;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.nickname = nickname;
    }
    public Long getReviewId() {
        return reviewId;
    }
    public String getReviewTitle() {
        return reviewTitle;
    }
    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
