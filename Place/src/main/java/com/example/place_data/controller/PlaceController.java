package com.example.place_data.controller;

import com.example.place_data.controller.request.RegisterPlaceWithAuthorizationRequest;
import com.example.place_data.controller.response.RegisterPlaceWithAuthorizationResponse;
import com.example.place_data.entity.Place;
import com.example.place_data.repository.PlaceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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



}
