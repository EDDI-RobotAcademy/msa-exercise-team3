package com.example.review.entity;

import jakarta.persistence.*;
import lombok.ToString;


    @Entity
    @ToString

    public class Review {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")

        private Long review_id;

        private String userId;
        private String nickname;
        private String reviewTitle;
        private String reviewContent;

        public Review(String userId, String nickname, String reviewTitle, String reviewContent) {
            this.userId = userId;
            this.nickname = nickname;
            this.reviewTitle = reviewTitle;
            this.reviewContent = reviewContent;
        }

        public Review() {
        }

        public Long getReview_id() {
            return review_id;
        }

        public String getUserId() {
            return userId;
        }

        public String getNickname() {
            return nickname;
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



    }



