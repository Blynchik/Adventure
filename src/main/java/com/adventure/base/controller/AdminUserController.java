package com.adventure.base.controller;

import com.adventure.base.model.AuthUser;
import com.adventure.base.model.role.ActionWithRole;
import com.adventure.base.model.role.Role;
import com.adventure.base.model.User;
import com.adventure.base.service.UserService;
import com.adventure.base.util.exception.ForbiddenActionException;
import com.adventure.base.util.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game/admin/user")
public class AdminUserController extends AbstractUserController {

    public AdminUserController(UserService userService) {
        super(userService);
    }

    @GetMapping()
    public ResponseEntity<?> getAll(@AuthenticationPrincipal AuthUser authUser) {
        List<User> users = userService.getAll();

        if (users.size() == 1) {
            return ResponseEntity.ok("Пользователей не найдено");
        }

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable int id) {
        return super.getOne(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping()
    public ResponseEntity<User> create(@RequestParam String name) {

        @Valid
        User user = new User(name);

        userService.createNew(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void changeRoles(@PathVariable int id,
                            @RequestParam ActionWithRole action,
                            @RequestParam Role role) {

        if (userService.checkExistence(id)) {

            switch (action) {
                case ADD -> userService.addRole(id, role);

                case REMOVE -> {
                    if (!role.equals(Role.USER)) {
                        userService.removeRole(id, role);
                    } else {
                        throw new ForbiddenActionException();
                    }
                }

            }

        } else {
            throw new UserNotFoundException(String.valueOf(id));
        }
    }
}
