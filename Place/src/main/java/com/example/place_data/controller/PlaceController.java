package com.example.place_data.controller;

import com.example.place_data.controller.request.PlaceSearchRequest;
import com.example.place_data.controller.request.RegisterPlaceWithAuthorizationRequest;
import com.example.place_data.controller.response.RegisterPlaceWithAuthorizationResponse;
import com.example.place_data.entity.Place;
import com.example.place_data.repository.PlaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/places")
public class PlaceController {

    private final PlaceRepository placeRepository;
    private final AccountClient accountClient;

    public PlaceController(PlaceRepository placeRepository, AccountClient accountClient) {
        this.placeRepository = placeRepository;
        this.accountClient = accountClient;
    }

    // 여행지 등록 API 구현
    @PostMapping
    public RegisterPlaceWithAuthorizationResponse register(
            @RequestHeader("Authorization") String token,
            @RequestBody RegisterPlaceWithAuthorizationRequest request) {
        log.info("Received request to register a new place");

        // userToken 획득
        String pureToken = extractToken(token);

        // FeignClient를 통해 account 서비스에 accountId 요청
        IdAccountResponse response = accountClient.getAccountId("Bearer " + pureToken);
        Long accountId = response.getAccountId();
        log.info("accountId = {}", accountId);

        Place requestedPlace = request.toRegister(accountId);
        Place registeredPlace = placeRepository.save(requestedPlace);
        return RegisterPlaceWithAuthorizationResponse.from(registeredPlace);
    }

    private String extractToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }

    // 여행지 목록 조회 API 구현
    @GetMapping
    public List<Place> listPlaces() {
        log.info("Received request to list all places");
        return placeRepository.findAll();
    }

    // 특정 여행지 상세 조회 API 구현 (1)
    @GetMapping("/{place_id}")
    public Place getPlaceById(@PathVariable Long place_id) {
        log.info("Received request to get a place by id: {}", place_id);
        return placeRepository.findById(place_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));
    }

    // 특정 여행지 상세 조회 API 구현 (2)
    @PostMapping("/search")
    public List<Place> searchPlaces(@RequestBody PlaceSearchRequest request) {
        String title = request.getTitle();
        String category = request.getCategory();
        String location = request.getLocation();

        // 이건 예시일 뿐이고, 실제로는 Specification이나 QueryDSL을 쓰는 게 더 좋습니다.
        if (title != null && category != null && location != null) {
            return placeRepository.findByTitleContainingAndCategoryAndLocation(title, category, location);
        } else if (title != null && category != null) {
            return placeRepository.findByTitleContainingAndCategory(title, category);
        } else if (title != null && location != null) {
            return placeRepository.findByTitleContainingAndLocation(title, location);
        } else if (title != null) {
            return placeRepository.findByTitleContaining(title);
        } else if (category != null && location != null) {
            return placeRepository.findByCategoryAndLocation(category, location);
        } else if (category != null) {
            return placeRepository.findByCategory(category);
        } else if (location != null) {
            return placeRepository.findByLocation(location);
        } else {
            return placeRepository.findAll();
        }
    }







}
