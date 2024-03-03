package com.acar.project.services;


import com.acar.project.entities.Post;
import com.acar.project.entities.User;
import com.acar.project.repos.PostRepository;
import com.acar.project.requests.PostCreateRequest;
import com.acar.project.requests.PostUpdateRequest;
import com.acar.project.response.LikeResponse;
import com.acar.project.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;
    private LikeService likeService;
    private UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
     }

    @Autowired
    public void setLikeService(@Lazy LikeService likeService) {
        this.likeService = likeService;
    }


    public List<PostResponse> getAllPosts(Optional<Long> userId) {

        List<Post> posts = userId.map(aLong -> postRepository.findByUserId(aLong)).orElseGet(() -> postRepository.findAll());

        return posts.stream().map(
                post -> {
                    List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.of(post.getId()), Optional.empty());
                    return new PostResponse(post, likes);
                }
        ).toList();

    }

    public Post getOnePost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null)
            return post;
        return null;
    }

    public PostResponse getOnePostWithLikes(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.of(postId), Optional.empty());

        if (post != null)
            return new PostResponse(post, likes);
        return null;
    }

    public Post createOnePost(PostCreateRequest newpPostRequest) {
        User user = userService.findById(newpPostRequest.getUserId());
        if (user == null)
            return null;

        Post post = new Post();
        post.setText(newpPostRequest.getText());
        post.setTitle(newpPostRequest.getTitle());
        post.setUser(user);
        post.setCreateDate(new Date());
        return postRepository.save(post);
    }

    public Post updateOnePost(Long postId, PostUpdateRequest postUpdateRequest) {
        Optional<Post> post = postRepository.findById(postId);

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
