package com.adventure.base.controller.user;

import com.adventure.base.dto.user.UserDto;
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
    public ResponseEntity<UserDto> getOwn(@AuthenticationPrincipal AuthUser authUser) {
        return super.getOne(authUser.id());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOwn(@AuthenticationPrincipal AuthUser authUser) {
        super.delete(authUser.id());
    }

    @GetMapping("/getAnotherByName")
    public ResponseEntity<UserDto> getAnother(@RequestParam String name) {
        return super.getAnother(name);
    }
}
