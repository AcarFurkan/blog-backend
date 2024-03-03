package com.acar.project.response;

import com.acar.project.entities.User;
import lombok.Data;

@Data
public class UserResponse {

    Long id;
     String userName;

    public UserResponse(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
    }
}