package com.acar.project.controllers;


import com.acar.project.entities.Like;
import com.acar.project.requests.LikeCreateRequest;
import com.acar.project.response.LikeResponse;
import com.acar.project.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return likeService.getAllLikesWithParam(userId, postId);
    }

    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest likeCreateRequest){
        return likeService.createOneLike(likeCreateRequest);
    }

    @GetMapping("{likeId}")
    public Like getLikeById(@PathVariable Long likeId){
        return likeService.getLikeById(likeId);
    }

    @DeleteMapping("{likeId}")
    public void deleteOneLike(@PathVariable Long likeId){
        likeService.deleteOneLike(likeId);
    }


}
