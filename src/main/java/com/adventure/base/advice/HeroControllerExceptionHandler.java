package com.adventure.base.advice;

import com.adventure.base.util.exception.ForbiddenActionException;
import com.adventure.base.util.exception.notFound.HeroNotFoundException;
import com.adventure.base.util.exceptionResponse.HeroExceptionResponse;
import com.adventure.base.util.exceptionResponse.UserExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HeroControllerExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<HeroExceptionResponse> handleException(HeroNotFoundException e){
        HeroExceptionResponse response = new HeroExceptionResponse(
                "Герой " + e.getMessage() + " не найден", LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<HeroExceptionResponse> handleException(ForbiddenActionException e) {
        HeroExceptionResponse response = new HeroExceptionResponse(
                e.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
