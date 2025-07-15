package com.example.review.controller.response;

import com.example.review.entity.Review;

public class UpdateReviewResponse {
    private Long reviewId;
    private Long placeId;
    private Long accountId;
    private String reviewTitle;
    private String reviewContent;

    public UpdateReviewResponse(Long reviewId, Long placeId, Long accountId, String reviewTitle, String reviewContent) {
        this.reviewId = reviewId;
        this.placeId = placeId;
        this.accountId = accountId;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;

    }

    public static UpdateReviewResponse from(Review review) {
        return new UpdateReviewResponse(
                review.getReviewId(),
                review.getPlaceId(),
                review.getAccountId(),
                review.getReviewTitle(),
                review.getReviewContent()
        );
    }
}
