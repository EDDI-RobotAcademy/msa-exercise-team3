package com.example.review.controller;

import com.example.review.client.AccountClient;
import com.example.review.controller.dto.request.comment.CommentsCreateRequest;
import com.example.review.controller.dto.request.comment.UpdateCommentsRequest;
import com.example.review.controller.dto.response.comment.CommentsCreateResponse;
import com.example.review.controller.dto.response.comment.CommentsResponse;
import com.example.review.service.CommentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/review/{reviewId}/comments")
public class CommentsController {

    private final CommentsService commentsService;
    private final AccountClient accountClient;

    @Autowired
    public CommentsController(CommentsService commentsService, AccountClient accountClient) {
        this.commentsService = commentsService;
        this.accountClient = accountClient;
    }

    // create comments
    @PostMapping
    public ResponseEntity<CommentsCreateResponse> createComment(@RequestBody CommentsCreateRequest commentsRequest,
                                                                @RequestHeader("Authorization") String token,
                                                                @PathVariable Long reviewId) {
        Long accountId = accountClient.getAccountId(token).getAccountId();
        log.info("Authorization header: {}", token);

        CommentsCreateResponse commentsResponse = commentsService.createComments(reviewId, commentsRequest, accountId);
        return new ResponseEntity<>(commentsResponse, HttpStatus.CREATED);
    }

    // read all comments of a review
    @GetMapping
    public ResponseEntity<List<CommentsResponse>> readComments(@PathVariable("reviewId") Long reviewId){
        List<CommentsResponse> comments = commentsService.readComments(reviewId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // update comments
    @PutMapping("/{id}")
    public ResponseEntity<CommentsResponse> updateComments(
            @RequestBody UpdateCommentsRequest updateRequest,
            @RequestHeader("Authorization") String token,
            @PathVariable("id") Long id
    ){
        Long accountId = accountClient.getAccountId(token).getAccountId();
        log.info("Authorization header: {}", token);
        CommentsResponse updatedComments = commentsService.updateComments(id, updateRequest, accountId);
        return new ResponseEntity<>(updatedComments, HttpStatus.OK);
    }

    // delete comments
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComments(@PathVariable("id") Long id,
                                               @RequestHeader("Authorization")String token){
        Long accountId = accountClient.getAccountId(token).getAccountId();
        log.info("Authorization header: {}", token);
        commentsService.deleteComments(id, accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}