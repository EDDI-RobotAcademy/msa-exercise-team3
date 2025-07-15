package com.example.review.controller.request;

import lombok.ToString;

@ToString
public class UpdateReviewRequest {

    Long review_Id;
    String nickname;
    String reviewTitle;
    String reviewContent;

    public UpdateReviewRequest(Long review_Id, String nickname, String reviewTitle, String reviewContent) {
        this.review_Id = review_Id;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.nickname = nickname;
    }
    public Long getReviewId() {
        return review_Id;
    }
    public String getReviewTitle() {
        return reviewTitle;
    }
    public String getReviewContent() {
        return reviewContent;
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
