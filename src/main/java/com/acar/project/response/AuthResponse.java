package com.acar.project.response;

import lombok.Data;

@Data
public class AuthResponse {
    String message;
    Long userId;
    String refreshToken;
    String accessToken;
}
