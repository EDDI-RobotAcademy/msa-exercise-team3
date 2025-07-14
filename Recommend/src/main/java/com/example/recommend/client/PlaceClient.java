package com.example.recommend.client;

//import com.example.book.controller.response.IdAccountResponse;
import com.example.recommend.dto.response.PlaceResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "place")
public interface PlaceClient {
    @GetMapping("/place/recommend")
    String getPlaceRecommendation(@RequestParam Long accountId);

    //입력된 keywords에 맞는 결과값을 받아오기위한 feign처리
    @GetMapping("/place/keywords")
    List<String> getPlacesByKeywords(@RequestParam("keywords") List<String> keywords);

    @GetMapping("/place/keyword")
    List<PlaceResponseDto> getPlacesByKeyword(
            @RequestParam("keyword") String keyword
    );
    @GetMapping("/place/location")
    List<PlaceResponseDto> getPlacesByLocation(
            @RequestParam("location") String location
    );
}