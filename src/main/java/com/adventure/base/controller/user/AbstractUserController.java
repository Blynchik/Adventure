package com.adventure.base.controller.user;

import com.adventure.base.dto.user.UserDto;
import com.adventure.base.model.User;
import com.adventure.base.service.UserService;
import com.adventure.base.util.Converter;
import com.adventure.base.util.exception.notFound.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;
import java.util.Optional;

public abstract class AbstractUserController {

    protected UserService userService;

    @Autowired
    public AbstractUserController(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<UserDto> getOneById(int id) {

        Optional<User> user = userService.getOneById(id);

        checkUserExistence(user, "id " + id);

        return ResponseEntity.ok().body(
                Converter.getEnrichedUserDto(user.get()));
    }

    public ResponseEntity<UserDto> getOneByName(String name) {

        Optional<User> user = userService.getByName(name);

        checkUserExistence(user, name);

        return ResponseEntity.ok(Converter.getUserDto(user.get()));
    }

    public ResponseEntity<UserDto> delete(int id) {

        checkUserExistence(id);

        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

    protected void checkUserExistence(int id) {

        if (!userService.idExistence(id)) {
            throw new UserNotFoundException("id " + id);
        }
    }

    protected void checkUserExistence(Optional<User> user, String name) {

        if (user.isEmpty()) {
            throw new UserNotFoundException(name);
        }
    }

}

