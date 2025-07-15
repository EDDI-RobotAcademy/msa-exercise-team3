package com.example.review.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.ToString;


    @Entity
    @ToString

    public class review {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long id;

        private String userId;
        private String nickname;
        private String reviewTitle;
        private String reviewContent;
        private Long accountId;

        public review(String userId, String nickname, String reviewTitle, String reviewContent) {
            this.userId = userId;
            this.nickname = nickname;
            this.reviewTitle = reviewTitle;
            this.reviewContent = reviewContent;
        }

        public review() {
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

        public review(String userId, String nickname, String reviewTitle, String reviewContent, Long accountId) {
            this.userId = userId;
            this.nickname = nickname;
            this.reviewTitle = reviewTitle;
            this.reviewContent = reviewContent;
            this.accountId = accountId;
        }

        public Long getAccountId() {
            return accountId;
        }

        public void setAccountId(Long accountId) {
            this.accountId = accountId;
        }
    }



