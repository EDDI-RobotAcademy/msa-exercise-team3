package com.example.review.controller.response;

import com.example.review.entity.Review;

public class RegisterReviewResponse {
    private String message;
    private Long reviewId;

    public RegisterReviewResponse() {
    }

    public RegisterReviewResponse(String message) {
        this.message = message;
    }
    public static RegisterReviewResponse from(Review review) {
        RegisterReviewResponse response = new RegisterReviewResponse();
        response.reviewId = review.getReviewId();
        response.message = "리뷰가 성공적으로 등록되었습니다.";
        return response;
    }
    public String getMessage() {
        return message;
    }
    public Long getReviewId() {
        return reviewId;
    }

}

