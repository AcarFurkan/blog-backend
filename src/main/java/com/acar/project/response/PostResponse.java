package com.acar.project.response;

import com.acar.project.entities.Like;
import com.acar.project.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private Long id;
    private Long userId;
    private String userName;
    private String title;
    private String text;
    List<LikeResponse> postLikes;

    public PostResponse(Post post, List<LikeResponse> postLikes) {
        this.id = post.getId();
        this.userId = post.getUser().getId();
        this.userName = post.getUser().getUserName();
        this.title = post.getTitle();
        this.text = post.getText();
        this.postLikes = postLikes;
    }
}
