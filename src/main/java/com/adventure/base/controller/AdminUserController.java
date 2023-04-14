package com.adventure.base.controller;

import com.adventure.base.model.role.ActionWithRole;
import com.adventure.base.model.role.Role;
import com.adventure.base.model.User;
import com.adventure.base.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game/admin/user")
public class AdminUserController extends AbstractUserController {

    public AdminUserController(UserService userService) {
        super(userService);
    }

    @GetMapping()
    public List<User> getAll() {
        return userService.getAll();
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addRole(@PathVariable int id,
                        @RequestParam ActionWithRole action,
                        @RequestParam Role role) {

        if (action.equals(ActionWithRole.ADD)) {
            userService.addRole(id, role);
        }

        if(action.equals(ActionWithRole.REMOVE)){
            userService.removeRole(id, role);
        }
    }
}
