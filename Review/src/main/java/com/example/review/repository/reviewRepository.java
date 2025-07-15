package com.example.review.repository;

import com.example.review.entity.review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface reviewRepository extends JpaRepository<review, Long> {
}
