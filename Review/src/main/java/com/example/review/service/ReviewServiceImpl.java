package com.example.review.service;

import com.example.review.controller.dto.request.review.RegisterReviewRequest;
import com.example.review.controller.dto.request.review.UpdateReviewRequest;
import com.example.review.controller.dto.response.review.RegisterReviewResponse;
import com.example.review.controller.dto.response.review.ReviewResponse;
import com.example.review.entity.Review;
import com.example.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    //register
    @Override
    public RegisterReviewResponse registerReview(RegisterReviewRequest registerRequest, Long accountId) {
        Review review = new Review(
                accountId,
                registerRequest.getPlaceId(),
                registerRequest.getTitle(),
                registerRequest.getDescription(),
                LocalDateTime.now()
        );

        Review savedReview = reviewRepository.save(review);
        return new RegisterReviewResponse(
                savedReview.getId(),
                savedReview.getAccountId(),
                savedReview.getPlaceId(),
                savedReview.getTitle(),
                savedReview.getDescription(),
                savedReview.getCreatedAt().toString()

        );
    }

    //read
    @Override
    public ReviewResponse readReview(Long id) {
        Review review = reviewRepository.findById(id).
                orElseThrow(() -> new RuntimeException("해당 리뷰를 찾을 수 없습니다."));
        return new ReviewResponse(
                review.getId(),
                review.getAccountId(),
                review.getPlaceId(),
                review.getTitle(),
                review.getDescription()
        );
    }

    @Override
    public List<ReviewResponse> readByPlaceId(Long placeId) {
        return reviewRepository.findByPlaceId(placeId).stream()
                .map(review -> new ReviewResponse(
                        review.getReviewId(),
                        review.getAccountId(),
                        review.getPlaceId(),
                        review.getTitle(),
                        review.getDescription()
                ))
                .toList();
    }

    //update
    @Override
    public ReviewResponse updateReview(Long id, UpdateReviewRequest updateRequest, Long accountId) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 리뷰를 찾을 수 없습니다."));

        if (!review.getAccountId().equals(accountId)) {
            throw new RuntimeException("작성자만 리뷰 수정할 수 있습니다.");
        }

        if (updateRequest.getTitle() != null) {
            review.setTitle(updateRequest.getTitle());
        }
        if (updateRequest.getDescription() != null) {
            review.setDescription(updateRequest.getDescription());
        }

        Review updatedReview = reviewRepository.save(review);
        return new ReviewResponse(
                updatedReview.getId(),
                updatedReview.getAccountId(),
                updatedReview.getPlaceId(),
                updatedReview.getTitle(),
                updatedReview.getDescription()
        );
    }

    //delete
    @Override
    public void deleteReview(Long id, Long accountId) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 리뷰를 찾을 수 없습니다."));

        if (!review.getAccountId().equals(accountId)) {
            throw new RuntimeException("작성자만 리뷰 수정할 수 있습니다.");
        }
        reviewRepository.deleteById(id);
    }
}