package com.example.review.controller.response;

import com.example.review.entity.Review;

public class UpdateReviewResponse {

    private Long reviewId;

    private  String userId;
    private  String nickname;
    private  String reviewTitle;
    private  String reviewContent;

    public UpdateReviewResponse(Long reviewId, String userId, String nickname, String reviewTitle, String reviewContent) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.nickname = nickname;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
    }

    public Long getId() {
        return reviewId;
    }
    public void setId(Long id) {
        this.reviewId = reviewId;
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
    public static UpdateReviewResponse from(Review review) {
        return new UpdateReviewResponse(
                review.getReviewId(),
                review.getUserId(),
                review.getNickname(),
                review.getReviewTitle(),
                review.getReviewContent()
        );
    }

}
