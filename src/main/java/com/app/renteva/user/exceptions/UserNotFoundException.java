package com.app.renteva.user.exceptions;

import com.app.renteva.shared.exceptions.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
