package com.osvaldevops.auth.infrastructure.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osvaldevops.auth.infrastructure.adapter.persistence.UserEntity;

public interface UserRepositoryJPA extends JpaRepository<UserEntity, Long> {
    
}
