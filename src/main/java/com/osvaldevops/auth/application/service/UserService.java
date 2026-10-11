package com.osvaldevops.auth.application.service;

import org.springframework.stereotype.Service;

import com.osvaldevops.auth.application.port.out.UserRepositoryPort;
import com.osvaldevops.auth.domain.model.User;
import com.osvaldevops.auth.application.port.in.CreateUser;

@Service 
public class UserService implements CreateUser
 {
    UserRepositoryPort userRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }
    
    @Override
    public User createUser(User user) {
        return userRepositoryPort.save(user);
    }
    
}
