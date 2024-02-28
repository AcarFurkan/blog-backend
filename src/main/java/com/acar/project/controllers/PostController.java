package com.acar.project.controllers;

import com.acar.project.entities.Post;
import com.acar.project.entities.User;
import com.acar.project.requests.PostCreateRequest;
import com.acar.project.requests.PostUpdateRequest;
import com.acar.project.response.PostResponse;
import com.acar.project.services.PostService;
import com.acar.project.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/posts")
public class PostController {


    private PostService postService;

    public PostController(PostService postService ) {
        this.postService = postService;
     }

    @GetMapping
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId) {
        return  postService.getAllPosts(userId);
    }

    @PostMapping Post createOnePost(@RequestBody PostCreateRequest post) {
        return postService.createOnePost(post);
    }

    @GetMapping("{postId}")
    public Post getOnePost(@PathVariable Long postId) {
        return postService.getOnePost(postId);
     }

     @PutMapping("{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest post) {
        return postService.updateOnePost(postId, post);
    }

    @DeleteMapping("{postId}")
    public void deleteOnePost(@PathVariable Long postId) {
        postService.deleteOnePost(postId);
    }
}
