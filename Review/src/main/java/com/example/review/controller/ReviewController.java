package com.example.review.controller;

import com.example.review.client.AccountClient;
import com.example.review.controller.request.RegisterReviewRequest;
import com.example.review.controller.request.UpdateReviewRequest;
import com.example.review.controller.response.IdAccountResponse;
import com.example.review.controller.response.UpdateReviewResponse;
import com.example.review.entity.review;
import com.example.review.repository.reviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/review")

public class ReviewController {
    @Autowired
    private reviewRepository reviewRepository;
    @Autowired
    private AccountClient accountClient;

    @PostMapping("/register")
    public review registerReview(@RequestBody RegisterReviewRequest register) {
        log.info("Registering review -> request: {}", register);
        review registeredReview = register.toReview();
        return reviewRepository.save(registeredReview);
    }
    @PostMapping("/update")
    public UpdateReviewResponse updateReview(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdateReviewRequest update) {
        log.info("Updating review -> request: {}", update);
        String pureToken = extractToken(token);
        IdAccountResponse response = accountClient.getAccountId("Bearer " + pureToken);
        Long accountId = response.getAccountId();

        Long updateReviewId = update.getReviewId();
        review foundReview = reviewRepository.findById(updateReviewId)
                .orElseThrow(() -> new RuntimeException("해당 리뷰는 존재하지 않습니다."));
        if(!foundReview.getAccountId().equals(accountId)){
            throw new RuntimeException("작성자 본인만 수정할 수 있습니다.");
        }
        foundReview.setReviewContent(update.getReviewContent());
        foundReview.setReviewTitle(update.getReviewTitle());

        review updatedReview = reviewRepository.save(foundReview);
        return UpdateReviewResponse.from(updatedReview);
    }
    private String extractToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }

    @GetMapping("/list")
    public List<review> readAllReviews() {
        log.info("Reading all reviews");
        return reviewRepository.findAll();
    }




    @GetMapping("/delete")
    public void deleteReview(@RequestParam Long reviewId) {
        log.info("Delete review -> reviewId: {}", reviewId);
        reviewRepository.deleteById(reviewId);
    }


}

