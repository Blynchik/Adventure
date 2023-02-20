package com.basic.game.controller;

import com.basic.game.model.User;
import com.basic.game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable final int id){
        return ResponseEntity.of(userService.get(id));
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        userService.create(user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        userService.delete(id);
    }

    @PatchMapping(value ="/{id}")
    public void update(@PathVariable int id,
                       @RequestBody User user){
        userService.update(user, id);
    }
}
