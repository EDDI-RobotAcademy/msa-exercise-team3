package com.example.place_data.controller.request;

import com.example.place_data.entity.Place;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPlaceWithAuthorizationRequest {
    private String title;
    private String content;
    private String category;
    private String location;
    private String address;

    public Place toRegister(Long accountId) {
        return new Place(title, content, category, location, address);
    }

}
