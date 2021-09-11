package com.app.renteva.user;

import com.app.renteva.user.resource.SingleAuthenticatedUserResource;

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
     * Returns a hash of the password.
     */
    String encodePassword(String password);

    /**
     * Map User from SingleAuthenticatedUserResource with the given token
     */
    SingleAuthenticatedUserResource getAuthenticatedUserFromUser(User user, String token);
}
