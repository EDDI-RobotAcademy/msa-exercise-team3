package com.example.place_data.controller.response;

import com.example.place_data.entity.Place;

public class RegisterPlaceWithAuthorizationResponse {

    private Long place_id;
    private String title;
    private String content;
    private String category;
    private String location;
    private String address;

    public RegisterPlaceWithAuthorizationResponse() {}

    public RegisterPlaceWithAuthorizationResponse(Long place_id, String title, String content, String category, String location, String address) {
        this.place_id = place_id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.location = location;
        this.address = address;
    }

    public static RegisterPlaceWithAuthorizationResponse from(Place place) {
        return new RegisterPlaceWithAuthorizationResponse(
                place.getPlace_id(),
                place.getTitle(),
                place.getContent(),
                place.getCategory(),
                place.getLocation(),
                place.getAddress()
        );
    }
}
