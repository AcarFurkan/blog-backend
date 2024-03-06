package com.acar.project.controllers;

import com.acar.project.entities.Comment;
import com.acar.project.requests.CommentCreateRequest;
import com.acar.project.requests.CommentUpdateRequest;
import com.acar.project.response.CommentResponse;
import com.acar.project.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentResponse> getAllComments(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId) {
       return commentService.getAllComments(postId, userId);
    }

    @GetMapping("{commentId}")
    public  CommentResponse getCommentById(@PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @PostMapping()
    public  Comment createOneComment(@RequestBody CommentCreateRequest comment) {
        return commentService.createOneComment(comment);
    }

    @PutMapping("{commentId}")
    public  Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest comment) {
        return commentService.updateOneComment(commentId, comment);
    }


    @DeleteMapping("{commentId}")
    public  void deleteOneComment(@PathVariable Long commentId) {
        commentService.deleteOneComment(commentId);
    }



}
