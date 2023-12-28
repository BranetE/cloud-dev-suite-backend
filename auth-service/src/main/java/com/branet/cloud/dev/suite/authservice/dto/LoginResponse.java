package com.branet.cloud.dev.suite.authservice.dto;

public record LoginResponse(String accessToken,
                            String refreshToken) {

}
