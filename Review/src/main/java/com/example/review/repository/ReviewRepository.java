package com.example.review.repository;

import com.example.review.controller.dto.response.review.ReviewResponse;
import com.example.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<ReviewResponse> findByPlaceId(Long placeId);
}
