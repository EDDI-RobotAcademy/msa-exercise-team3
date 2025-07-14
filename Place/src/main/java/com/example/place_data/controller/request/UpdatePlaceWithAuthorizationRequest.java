package com.example.place_data.controller.request;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePlaceWithAuthorizationRequest {
    private String title;
    private String content;
    private String category;
    private String location;
    private String address;
}
