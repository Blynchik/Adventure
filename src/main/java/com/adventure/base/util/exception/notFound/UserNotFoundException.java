package com.adventure.base.util.exception.notFound;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
    }

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
