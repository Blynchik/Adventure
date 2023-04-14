package com.adventure.base.controller;

import com.adventure.base.model.User;
import com.adventure.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public abstract class AbstractUserController {

    protected UserService userService;

    @Autowired
    public AbstractUserController(UserService userService){
        this.userService = userService;
    }

    public ResponseEntity<User> getOne(int id) {
        return ResponseEntity.ok().body(
                userService.getOneById(id));
    }

    public void delete(int id) {
        userService.delete(id);
        ResponseEntity.ok().build();
    }

    public ResponseEntity<User> getAnother(String name){
        return ResponseEntity.ok(
                userService.getByName(name));
    }
}
