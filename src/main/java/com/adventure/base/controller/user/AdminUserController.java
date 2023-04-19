package com.adventure.base.controller.user;

import com.adventure.base.dto.user.UserDto;
import com.adventure.base.dto.user.UserDtoForCreating;
import com.adventure.base.model.role.ActionWithRole;
import com.adventure.base.model.role.Role;
import com.adventure.base.model.User;
import com.adventure.base.service.UserService;
import com.adventure.base.util.Converter;
import com.adventure.base.util.exception.ForbiddenActionException;
import com.adventure.base.util.exception.notFound.UserNotFoundException;
import com.adventure.base.util.validator.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/game/admin/user")
public class AdminUserController extends AbstractUserController {

    private final UserValidator userValidator;

    @Autowired
    public AdminUserController(UserService userService,
                               UserValidator userValidator) {
        super(userService);
        this.userValidator = userValidator;
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {

        List<UserDto> users = userService.getAll()
                .stream()
                .map(Converter::getUserDto)
                .collect(Collectors.toList());

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
    public ResponseEntity<?> create(@Valid @RequestBody UserDtoForCreating userDto,
                                    BindingResult bindingResult) {

        userValidator.validate(userDto, bindingResult);


        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getAllErrors().stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage));
        }

        userService.createNew(Converter.getUser(userDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(
                userService.getByName(userDto.getName()).get());
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void changeRoles(@PathVariable int id,
                            @RequestParam ActionWithRole action,
                            @RequestParam Role role) {

        if (userService.idExistence(id)) {

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
