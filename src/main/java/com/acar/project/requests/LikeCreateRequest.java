package com.acar.project.requests;


import lombok.Data;

@Data
public class LikeCreateRequest {
    private Long postId;
    private Long userId;
}
