package com.app.renteva.user;

import java.util.Optional;

public interface UserService {

    /**
     * Returns current authenticated user.
     */
    Optional<User> getCurrentUser();

    /**
     * Returns a JWT token used to authenticate current user.
     */
    Optional<String> getCurrentToken();

    /**
     * Checks if provided credentials correct and returns relevant user.
     */
    Optional<User> authenticate(String email, String password);

    /**
     * Register a new user.
     */
    Optional<User> register();

    /**
     * Returns a hash of the password.
     */
    String encodePassword(String password);
}
