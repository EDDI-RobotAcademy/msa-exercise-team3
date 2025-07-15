package com.example.review.controller;

import com.example.review.client.AccountClient;
import com.example.review.controller.request.RegisterReviewRequest;
import com.example.review.controller.request.UpdateReviewRequest;
import com.example.review.controller.response.IdAccountResponse;
import com.example.review.controller.response.UpdateReviewResponse;
import com.example.review.entity.Review;
import com.example.review.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/review")

public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private AccountClient accountClient;

    @PostMapping("/register")
    public Review registerReview(@RequestBody RegisterReviewRequest register) {
        log.info("Registering review -> request: {}", register);
        Review registeredReview = register.toReview();
        return reviewRepository.save(registeredReview);
    }

    @PostMapping("/update")
    public UpdateReviewResponse updateReview(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdateReviewRequest update) {

        String pureToken = extractToken(token);
        IdAccountResponse response = accountClient.getAccountId("Bearer " + pureToken);

        String requestUserId = response.getUserId();

        Review foundReview = reviewRepository.findById(update.getReviewId())
                .orElseThrow(() -> new RuntimeException("해당 리뷰는 존재하지 않습니다."));

        if (!foundReview.getUserId().equals(requestUserId)) {
            return null;
        }

        foundReview.setReviewContent(update.getReviewContent());
        foundReview.setReviewTitle(update.getReviewTitle());

        Review updatedReview = reviewRepository.save(foundReview);
        return UpdateReviewResponse.from(updatedReview);
    }
    private String extractToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }


    @GetMapping("/list")
    public List<Review> readAllReviews() {
        log.info("Reading all reviews");
        return reviewRepository.findAll();
    }
    @GetMapping("/read/{nickname}")
    public List<Review> readByNickname(@PathVariable String nickname) {
        log.info("Reading reviews by nickname: {}", nickname);
        return reviewRepository.findAllByNickname(nickname);
    }


    @DeleteMapping("/delete")
    public void deleteReview(@RequestParam Long reviewId) {
        log.info("Delete review -> reviewId: {}", reviewId);
        reviewRepository.deleteById(reviewId);
    }


}

