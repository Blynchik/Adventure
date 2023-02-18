package com.basic.game.controller;

import com.basic.game.model.User;
import com.basic.game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = UserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    static final String REST_URL = "/users";

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable int id){
        return userService.get(id).orElseThrow();
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(User user){
        userService.create(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        userService.delete(id);
    }

    @PutMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(int id, User user){
        userService.update(user, id);
    }
}
