package com.acar.project.services;


import com.acar.project.entities.Post;
import com.acar.project.entities.User;
import com.acar.project.repos.PostRepository;
import com.acar.project.requests.PostCreateRequest;
import com.acar.project.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private  PostRepository postRepository;
    private UserService userService;

    public  PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }


     public List<Post> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent()) {
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }

     public  Post   getOnePost( Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newpPostRequest) {
        User user =  userService.findById(newpPostRequest.getUserId());
        if (user == null)
            return null;

        Post post = new Post();
        post.setText(newpPostRequest.getText());
        post.setTitle(newpPostRequest.getTitle());
        post.setUser(user);
        return postRepository.save(post);
    }

    public Post updateOnePost(Long postId, PostUpdateRequest postUpdateRequest) {
       Optional<Post> post =  postRepository.findById(postId);

        if (post.isPresent()) {
            Post newPost = new Post();
            newPost.setText(postUpdateRequest.getText());
            newPost.setTitle(postUpdateRequest.getTitle());
            newPost.setUser(post.get().getUser());
            newPost.setId(post.get().getId());
            return postRepository.save(newPost);
        }
        return null;
    }

    public void deleteOnePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
