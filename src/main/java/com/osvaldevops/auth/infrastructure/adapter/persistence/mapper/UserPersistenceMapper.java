package com.osvaldevops.auth.infrastructure.adapter.persistence.mapper;

import com.osvaldevops.auth.domain.model.User;
import com.osvaldevops.auth.infrastructure.adapter.persistence.UserEntity;

public final class UserPersistenceMapper {

    private UserPersistenceMapper() {
    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(user.getUsername(), user.getEmail(), user.getPassword(), user.getStatus());
    }

    public static User toDomain(UserEntity entity) {
        User user = new User(entity.getUsername(), entity.getEmail(), entity.getPassword(), entity.getStatus());
        user.setId(entity.getId());
        return user;
    }
}