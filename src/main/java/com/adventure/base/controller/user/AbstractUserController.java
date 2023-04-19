package com.adventure.base.controller.user;

import com.adventure.base.model.User;
import com.adventure.base.service.UserService;
import com.adventure.base.util.exception.notFound.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public abstract class AbstractUserController {

    protected UserService userService;

    @Autowired
    public AbstractUserController(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<User> getOne(int id) {

        Optional<User> user = userService.getOneById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException(String.valueOf(id));
        }

        return ResponseEntity.ok().body(
                user.get());
    }

    public void delete(int id) {
        if (userService.idExistence(id)) {
            userService.delete(id);
        } else {
            throw new UserNotFoundException(String.valueOf(id));
        }
    }

    public ResponseEntity<User> getAnother(String name) {

        Optional<User> user = userService.getByName(name);

        if (user.isEmpty()) {
            throw new UserNotFoundException(name);
        }

        return ResponseEntity.ok(user.get());
    }
}
