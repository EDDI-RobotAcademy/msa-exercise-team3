package com.example.place_data.controller.response;

import com.example.place_data.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePlaceResponse {
    private String title;
    private String content;
    private String category;
    private String location;
    private String address;

    public static UpdatePlaceResponse from(Place place) {
        return new UpdatePlaceResponse(
                place.getTitle(),
                place.getContent(),
                place.getCategory(),
                place.getLocation(),
                place.getAddress()
        );
    }
}
