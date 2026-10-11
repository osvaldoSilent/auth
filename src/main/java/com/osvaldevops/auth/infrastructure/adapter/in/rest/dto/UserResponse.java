package com.osvaldevops.auth.infrastructure.adapter.in.rest.dto;

public record UserResponse(
        Long id,
        String username,
        String email,
        String status) {
}