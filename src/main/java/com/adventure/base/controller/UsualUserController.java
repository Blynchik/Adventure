package com.adventure.base.controller;

import com.adventure.base.model.AuthUser;
import com.adventure.base.model.User;
import com.adventure.base.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/game/user")
public class UsualUserController extends AbstractUserController {

    public UsualUserController(UserService userService) {
        super(userService);
    }

    @GetMapping
    public ResponseEntity<User> getOwn(@AuthenticationPrincipal AuthUser authUser) {
        return super.getOne(authUser.id());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOwn(@AuthenticationPrincipal AuthUser authUser) {
        super.delete(authUser.id());
    }

    @GetMapping("/getAnother")
    public ResponseEntity<User> getAnother(@RequestParam String name) {
        return super.getAnother(name);
    }
}
