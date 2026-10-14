package com.osvaldevops.auth.application.port.in;

import com.osvaldevops.auth.domain.model.User;

/**
 * CreateUserUseCase
 */
public interface CreateUserUseCase {

    public User creatUser(User user);
}