package com.example.review.repository;

import com.example.review.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    List<Comments> findByReviewId(Long reviewId);
}
