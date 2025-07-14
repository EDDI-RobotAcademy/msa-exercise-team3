package com.example.recommend.dto.response;

import lombok.Data;

@Data
public class RecommendResultResponse {
    private String nickname;
    private String recommendedPlace;

    public RecommendResultResponse(String recommendedPlace, String nickname) {
        this.recommendedPlace = recommendedPlace;
        this.nickname = nickname;
    }
}