package com.example.review.entity;

import jakarta.persistence.*;
import lombok.ToString;


    @Entity
    @ToString

    public class Review {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        
            private Long reviewId;
            private String userId;
            private String nickname;
            private String reviewTitle;
            private String reviewContent;
            private Long placeId;  // 추가

            public Review(String userId, String nickname, String reviewTitle, String reviewContent, Long placeId) {
                this.userId = userId;
                this.nickname = nickname;
                this.reviewTitle = reviewTitle;
                this.reviewContent = reviewContent;
                this.placeId = placeId;
            }

        public Review() {
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

        public Long getPlaceId() {
            return placeId;
        }

        public void setPlaceId(Long placeId) {

        }

        public Long getReviewId() {
                return reviewId;
        }
    }



