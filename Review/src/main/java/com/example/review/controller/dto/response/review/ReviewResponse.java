package com.example.review.controller.dto.response.review;

public class ReviewResponse {
    private Long reviewId;
    private Long accountId;
    private Long placeId;
    private String title;
    private String description;


    public ReviewResponse() {}
    public ReviewResponse(Long reviewId,Long accountId, Long placeId, String title, String description) {
        this.reviewId = reviewId;
        this.accountId = accountId;
        this.placeId = placeId;
        this.title = title;
        this.description = description;

    }

    public Long getReviewId() {
        return reviewId;
    }
    public Long getAccountId() {
        return accountId;
    }
    public Long getPlaceId() {return placeId;}
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }


}
