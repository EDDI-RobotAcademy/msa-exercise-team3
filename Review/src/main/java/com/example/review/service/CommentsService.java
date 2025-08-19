package com.example.review.service;

import com.example.review.controller.dto.request.comment.CommentsCreateRequest;
import com.example.review.controller.dto.request.comment.UpdateCommentsRequest;
import com.example.review.controller.dto.response.comment.CommentsCreateResponse;
import com.example.review.controller.dto.response.comment.CommentsResponse;

import java.util.List;

public interface CommentsService {
    CommentsCreateResponse createComments(Long reviewId, CommentsCreateRequest commentsRequest,Long accountId);

    CommentsResponse updateComments(Long id, UpdateCommentsRequest updateRequest, Long accountId);

    void deleteComments(Long id, Long accountId);

    List<CommentsResponse> readComments(Long reviewId);
}
