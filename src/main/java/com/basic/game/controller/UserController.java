package com.basic.game.controller;

import com.basic.game.dto.UserTo;
import com.basic.game.model.User;
import com.basic.game.service.UserService;
import com.basic.game.util.Converter;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    public static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable final int id) {
        log.info("UserController начал getOne id:{}", id);

        if (userService.get(id).isPresent()) {
            return ResponseEntity.ok().body(userService.get(id).get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        log.info("UserController начал getAll");
        return ResponseEntity.ok().body(userService.getAll());
    }

    @PostMapping()
    public ResponseEntity<Object> create(@RequestBody @Valid UserTo userTo,
                                       BindingResult bindingResult) {
        log.info("UserController начал create");

        if (bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        User user = new User();
        userService.create(Converter.getUser(user, userTo));
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<User>> delete(@PathVariable int id) {
        log.info("UserController начал delete id:{}", id);


        if (userService.get(id).isPresent()) {
            userService.delete(id);
            return ResponseEntity.ok().body(userService.getAll());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id,
                                       @RequestBody @Valid UserTo userTo,
                                       BindingResult bindingResult) {
        log.info("UserController начал update id:{}", id);

        if (bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        if (userService.get(id).isPresent()) {
            userService.update(Converter.getUser(userService.get(id).get(), userTo), id);
            return ResponseEntity.ok().body(userService.get(id).get());
        }

        return ResponseEntity.notFound().build();
    }
}
