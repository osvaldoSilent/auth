package com.osvaldevops.auth.infrastructure.adapter.in.rest.mapper;

import com.osvaldevops.auth.domain.model.User;
import com.osvaldevops.auth.infrastructure.adapter.in.rest.dto.CreateUserRequest;
import com.osvaldevops.auth.infrastructure.adapter.in.rest.dto.UserResponse;

public final class UserRestMapper {

    private UserRestMapper() {
    }

    public static User toDomain(CreateUserRequest request) {
        return new User(request.username(), request.email(), request.password(), request.status());
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getStatus());
    }
}