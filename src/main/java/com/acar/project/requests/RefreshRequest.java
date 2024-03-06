package com.acar.project.requests;

import lombok.Data;

@Data
public class RefreshRequest {
    String refreshToken;
    Long userId;
}
