package com.osvaldevops.auth.application.port.out;

import com.osvaldevops.auth.domain.model.User;

public interface UserRepositoryPort {
    User save(User user);
}
