package com.adventure.base.controller;

import com.adventure.base.dto.UserDto;
import com.adventure.base.model.User;
import com.adventure.base.service.UserService;
import com.adventure.base.service.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody UserDto user) {
        userService.create(
                UserUtil.prepareToSave(user));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        return ResponseEntity.ok().body(userService.getUserById(id).orElse(null));
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<HttpStatus> changeRole(@PathVariable int id,
//                                                 @RequestParam Role role) {
//        userService.update(id,
//                UserUtil.prepareToSave(user));
//        return ResponseEntity.ok().build();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
