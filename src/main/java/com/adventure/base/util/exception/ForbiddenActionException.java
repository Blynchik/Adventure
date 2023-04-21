package com.adventure.base.util.exception;

public class ForbiddenActionException extends RuntimeException{

    public ForbiddenActionException(String message) {
        super(message);
    }
}
