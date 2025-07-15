package com.example.review.client;

import com.example.review.controller.response.SearchPlaceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "place") // or url = "http://localhost:8082" if testing locally
public interface PlaceClient {
    @GetMapping("/place/{place_id}")
    SearchPlaceResponse getPlaceById(@PathVariable("place_id") Long placeId,
                                     @RequestHeader("Authorization") String token);
}
