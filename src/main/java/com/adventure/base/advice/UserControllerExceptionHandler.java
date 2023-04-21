package com.adventure.base.advice;

import com.adventure.base.util.exception.ForbiddenActionException;
import com.adventure.base.util.exception.notFound.UserNotFoundException;
import com.adventure.base.util.exceptionResponse.UserExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class UserControllerExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<UserExceptionResponse> handleException(UserNotFoundException e) {
        UserExceptionResponse response = new UserExceptionResponse(
                "Пользователь " + e.getMessage() + " не найден", LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<UserExceptionResponse> handleException(ForbiddenActionException e) {
        UserExceptionResponse response = new UserExceptionResponse(
        e.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
