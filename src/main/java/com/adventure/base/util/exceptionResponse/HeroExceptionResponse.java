package com.adventure.base.util.exceptionResponse;

import java.time.LocalDateTime;

public class HeroExceptionResponse extends ExceptionResponse {

    public HeroExceptionResponse(String message, LocalDateTime dateTime) {
        super(message, dateTime);
    }
}
