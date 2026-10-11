package com.osvaldevops.auth.infrastructure.adapter.in.rest.dto;

public record CreateUserRequest(
        String username,
        String email,
        String password,
        String status) {
}