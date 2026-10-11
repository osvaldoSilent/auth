package com.osvaldevops.auth.infrastructure.adapter.in.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osvaldevops.auth.application.port.in.CreateUser;
import com.osvaldevops.auth.domain.model.User;
import com.osvaldevops.auth.infrastructure.adapter.in.rest.dto.CreateUserRequest;
import com.osvaldevops.auth.infrastructure.adapter.in.rest.dto.UserResponse;
import com.osvaldevops.auth.infrastructure.adapter.in.rest.mapper.UserRestMapper;

@RestController
@RequestMapping("/api/login")
public class UserController {
    private final CreateUser createUser;
    
    public UserController(CreateUser createUser) {
        this.createUser = createUser;
    }
     
    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        User user = createUser.createUser(UserRestMapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserRestMapper.toResponse(user));
    }

}
