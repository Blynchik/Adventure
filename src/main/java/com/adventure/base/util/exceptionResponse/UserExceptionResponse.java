package com.adventure.base.util.exceptionResponse;

import java.time.LocalDateTime;

public class UserExceptionResponse extends ExceptionResponse{

    public UserExceptionResponse(String message, LocalDateTime dateTime) {
        super(message, dateTime);
    }
}
