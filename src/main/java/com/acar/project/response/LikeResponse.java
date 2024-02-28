package com.acar.project.response;


import com.acar.project.entities.Like;
import lombok.Data;

@Data
public class LikeResponse {
    private Long id;
    private Long postId;
    private Long userId;

    public LikeResponse(Like like) {
        this.id = like.getId();
        this.postId = like.getPost().getId();
        this.userId = like.getUser().getId();
    }
}
