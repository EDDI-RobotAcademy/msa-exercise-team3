package com.example.review.controller;

import com.example.review.client.AccountClient;
import com.example.review.controller.dto.request.review.RegisterReviewRequest;
import com.example.review.controller.dto.request.review.UpdateReviewRequest;
import com.example.review.controller.dto.response.IdAccountResponse;
import com.example.review.controller.dto.response.review.RegisterReviewResponse;
import com.example.review.controller.dto.response.review.ReviewResponse;
import com.example.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor

public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private AccountClient accountClient;

    //리뷰 생성
    @PostMapping("/register")
    public ResponseEntity<RegisterReviewResponse> registerReview(
            @RequestHeader("Authorization") String token,
            @RequestBody RegisterReviewRequest registerRequest) {
        IdAccountResponse idAccountResponse = accountClient.getAccountId(token);
        Long accountId = idAccountResponse.getAccountId();
        log.info("Authorization header: {}", token);

        RegisterReviewResponse reviewResponse = reviewService.registerReview(registerRequest, accountId);
        return new ResponseEntity<>(reviewResponse, HttpStatus.CREATED);
    }
    private String extractToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }
    //리뷰 조회
    @GetMapping("/{id}")
    public ReviewResponse readReview (@PathVariable("id") Long id){
        return reviewService.readReview(id);
    }
    @GetMapping("/place/{placeId}")
    public List<ReviewResponse> readbyplaceId(@PathVariable("placeId") Long placeId){
        return reviewService.readByPlaceId(placeId);
    }

    //리뷰수정
    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String token,
            @RequestBody UpdateReviewRequest request) {

        Long accountId = accountClient.getAccountId(token).getAccountId();
        log.info("Authorization header: {}", token);

        ReviewResponse updatedReview = reviewService.updateReview(id, request, accountId);
        return new ResponseEntity<>(updatedReview, HttpStatus.UPGRADE_REQUIRED);
    }

    //리뷰삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ReviewResponse> deleteReview(@PathVariable("id") Long id,
                                               @RequestHeader("Authorization") String token) {
        Long accountId = accountClient.getAccountId(token).getAccountId();
        log.info("Authorization header: {}", token);

        reviewService.deleteReview(id, accountId);

        // 삭제 성공 시, 200 OK 상태 코드와 함께 메시지 반환
        return new ResponseEntity<>(HttpStatus.OK);
    }










}
