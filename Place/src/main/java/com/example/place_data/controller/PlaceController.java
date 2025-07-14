package com.example.place_data.controller;

import com.example.place_data.controller.request.PlaceSearchRequest;
import com.example.place_data.controller.request.RegisterPlaceWithAuthorizationRequest;
import com.example.place_data.controller.request.UpdatePlaceWithAuthorizationRequest;
import com.example.place_data.controller.response.RegisterPlaceWithAuthorizationResponse;
import com.example.place_data.controller.response.UpdatePlaceResponse;
import com.example.place_data.entity.Place;
import com.example.place_data.repository.PlaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    // 여행지 수정 API 구현 (인증받은 사용자만 여행지 수정 가능)
    @PostMapping("/update")
    public UpdatePlaceResponse updatePlace(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdatePlaceWithAuthorizationRequest request) {
        log.info("Received request to update a place");

        // userToken 획득
        String pureToken = extractToken(token);

        // FeignClient를 통해 account 서비스에 accountId 요청
        IdAccountResponse response = accountClient.getAccountId("Bearer " + pureToken);
        Long accountId = response.getAccountId();
        log.info("accountId = {}", accountId);

        Long place_id = request.getPlace_id();
        Optional<Place> maybePlace = placeRepository.findById(place_id);

        if(maybePlace.isEmpty()){
            return null;
        }
        Place foundPlace = maybePlace.get();
        foundPlace.setTitle(request.getTitle());
        foundPlace.setContent(request.getContent());
        foundPlace.setCategory(request.getCategory());
        foundPlace.setLocation(request.getLocation());
        foundPlace.setAddress(request.getAddress());
        Place updatedPlace = placeRepository.save(foundPlace);
        return UpdatePlaceResponse.from(updatedPlace);
    }










}
