package com.fiscos.atlas.core.application.mapper;

import com.fiscos.atlas.core.application.dto.UserResponse;
import com.fiscos.atlas.core.domain.model.aggregate.User;

public class UserDataMapper {
    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}