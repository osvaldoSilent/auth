package com.osvaldevops.auth.infrastructure.adapter.out;
import org.springframework.stereotype.Repository;

import com.osvaldevops.auth.application.port.out.UserRepositoryPort;
import com.osvaldevops.auth.domain.model.User;
import com.osvaldevops.auth.infrastructure.adapter.persistence.UserEntity;
import com.osvaldevops.auth.infrastructure.adapter.persistence.mapper.UserPersistenceMapper;

@Repository
public class UserRepository implements UserRepositoryPort {
    UserRepositoryJPA userRepositoryJPA;

    public UserRepository(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    public User save(User user) {
        UserEntity savedUserEntity = userRepositoryJPA.save(UserPersistenceMapper.toEntity(user));
        return UserPersistenceMapper.toDomain(savedUserEntity);
    }
}
