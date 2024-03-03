package com.acar.project.services;

import com.acar.project.entities.Comment;
import com.acar.project.entities.Post;
import com.acar.project.entities.User;
import com.acar.project.repos.CommentRepository;
import com.acar.project.requests.CommentCreateRequest;
import com.acar.project.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class CommentService {
    private CommentRepository commentRepository;
    private PostService postService;
    private UserService userService;

    public CommentService(CommentRepository commentRepository, PostService postService, UserService userService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.userService = userService;
    }


    public List<Comment> getAllComments(Optional<Long> postId, Optional<Long> userId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        } else
            return commentRepository.findAll();
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }


    public Comment createOneComment(CommentCreateRequest comment) {
        User user = userService.findById(comment.getUserId());
        Post post = postService.getOnePost(comment.getPostId());
        if (user != null && post != null) {
            Comment newComment = new Comment();
            newComment.setUser(user);
            newComment.setPost(post);
            newComment.setContent(comment.getText());
            newComment.setCreateDate(new Date());
            return commentRepository.save(newComment);
        }
        return null;

    }

    public Comment updateOneComment(Long commentId, CommentUpdateRequest comment) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            Comment commentToUpdate = commentOptional.get();
            commentToUpdate.setContent(comment.getText());
            return commentRepository.save(commentToUpdate);
        }
        return null;
    }

    public void deleteOneComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
