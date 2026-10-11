package com.osvaldevops.auth.application.port.in;

import com.osvaldevops.auth.domain.model.User;

public interface CreateUser {
    User createUser(User user);
}