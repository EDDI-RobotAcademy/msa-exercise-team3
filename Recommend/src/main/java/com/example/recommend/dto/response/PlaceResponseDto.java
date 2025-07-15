package com.example.recommend.dto.response;

import lombok.Data;

@Data
public class PlaceResponseDto {
    private Long placeId;     // 장소 클릭 시 ID 필요할 수 있음
    private String title;     // 제목
    private String content;   // 내용 요약
    private String category;  // 테마 (HEALING, NIGHTLIFE 등)
    private String location;  // 지역
    private String address;
    private Long accountId;
//    public PlaceResponseDto(Long placeId, String title, String content, String category, String location) {
//        this.placeId = placeId;
//        this.title = title;
//        this.content = content;
//        this.category = category;
//        this.location = location;
//    }
    public PlaceResponseDto(Long placeId, String title, String content, String category, String location, String address, Long accountId) {
        this.placeId = placeId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.location = location;
        this.address = address;
        this.accountId = accountId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public String getAddress() {
        return address;
    }

    public Long getAccountId() {
        return accountId;
    }
}