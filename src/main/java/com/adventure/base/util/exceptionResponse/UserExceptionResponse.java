package com.adventure.base.util.exceptionResponse;

import java.time.LocalDateTime;

public class UserExceptionResponse {
    private String message;
    private LocalDateTime dateTime;

    public UserExceptionResponse(String message, LocalDateTime dateTime) {
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
