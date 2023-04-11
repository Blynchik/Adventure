package com.adventure.base.controller;

import com.adventure.base.model.AuthUser;
import com.adventure.base.model.User;
import com.adventure.base.service.UserService;
import com.adventure.base.util.exception.UserNotFoundException;
import com.adventure.base.util.exceptionResponse.UserExceptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/game/user")
public class UsualUserController {

    private final UserService userService;

    @Autowired
    public UsualUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<User> getOwn(@AuthenticationPrincipal AuthUser authUser) {
        return ResponseEntity.ok().body(
                userService.getOneById(authUser.id()));
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteOwn(@AuthenticationPrincipal AuthUser authUser) {
        userService.delete(authUser.id());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findAnother")
    public ResponseEntity<User> getAnother(@RequestParam String name){
       return ResponseEntity.ok(
               userService.getByName(name));
    }
}
