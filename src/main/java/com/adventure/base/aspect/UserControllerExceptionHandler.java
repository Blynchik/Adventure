package com.adventure.base.aspect;

import com.adventure.base.util.exception.EmptyListException;
import com.adventure.base.util.exception.ForbiddenActionException;
import com.adventure.base.util.exception.UserNotFoundException;
import com.adventure.base.util.exceptionResponse.UserExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;

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
    private ResponseEntity<UserExceptionResponse> handleException(EmptyListException e) {
        UserExceptionResponse response = new UserExceptionResponse(
                "Ничего не найдено", LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    private ResponseEntity<UserExceptionResponse> handleException(ForbiddenActionException e) {
        UserExceptionResponse response = new UserExceptionResponse(
                "Нельзя удалить права пользователя", LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
