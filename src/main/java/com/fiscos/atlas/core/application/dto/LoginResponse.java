package com.fiscos.atlas.core.application.dto;

public record LoginResponse(String token, UserResponse user) {
}