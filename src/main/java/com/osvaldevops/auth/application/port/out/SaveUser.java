package com.osvaldevops.auth.application.port.out;

import com.osvaldevops.auth.domain.model.User;

/**
 * SaveUser
 */
public interface SaveUser {

    public void save(User user);
}