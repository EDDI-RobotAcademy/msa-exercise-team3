package com.example.place_data.controller.request;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlaceSearchRequest {
    private String title;
    private String category;
    private String location;
}
