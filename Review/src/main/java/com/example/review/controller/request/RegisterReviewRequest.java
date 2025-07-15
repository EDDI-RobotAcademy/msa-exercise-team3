package com.example.review.controller.request;

import com.example.review.entity.Review;
import lombok.ToString;

@ToString
public class RegisterReviewRequest {
    private Long placeId;

    private String userId;
    private String nickname;
    private String reviewTitle;
    private String reviewContent;

    public RegisterReviewRequest() {

    }

    public RegisterReviewRequest(Long placeId, String userId, String nickname, String reviewTitle, String reviewContent) {
        this.placeId = placeId;
        this.userId = userId;
        this.nickname = nickname;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }
    public Review toReview() {
        return new Review(userId, nickname, reviewTitle, reviewContent, placeId);
    }
}
