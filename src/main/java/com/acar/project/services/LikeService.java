package com.acar.project.services;

import com.acar.project.entities.Like;
import com.acar.project.entities.Post;
import com.acar.project.entities.User;
import com.acar.project.repos.LikeRepository;
import com.acar.project.requests.LikeCreateRequest;
import com.acar.project.response.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private LikeRepository likeRepository;

    private UserService userService;

    private PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {

        if (userId.isPresent() && postId.isPresent()) {
            return likeRepository.findByUserIdAndPostId(userId.get(), postId.get()).stream().map(LikeResponse::new).toList();
        } else
            return userId.map(aLong -> likeRepository.findByUserId(aLong).stream().map(LikeResponse::new).toList()).orElseGet(() ->
                    postId.map(aLong -> likeRepository.findByPostId(aLong).stream().map(LikeResponse::new).toList()).orElseGet(() ->
                            likeRepository.findAll().stream().map(LikeResponse::new).toList()));
    }

    public Like createOneLike(LikeCreateRequest likeCreateRequest) {
        User user = userService.findById(likeCreateRequest.getUserId());
        Post post = postService.getOnePost(likeCreateRequest.getPostId());
        if (user != null && post != null) {
            Like like = new Like();
            like.setUser(user);
            like.setPost(post);
            return likeRepository.save(like);
        }
        return null;
    }

    public void deleteOneLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }

    public Like getLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }


}
