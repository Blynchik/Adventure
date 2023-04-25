package com.adventure.base.controller;

import com.adventure.base.dto.user.UserDtoForCreating;
import com.adventure.base.model.User;
import com.adventure.base.service.RegistrationService;
import com.adventure.base.util.Converter;
import com.adventure.base.util.validator.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game/login")
public class RegistrationController {

    private final RegistrationService registrationService;
    private  final UserValidator userValidator;

    @Autowired
    public RegistrationController(RegistrationService registrationService,
                                  UserValidator userValidator){
        this.registrationService = registrationService;
        this.userValidator = userValidator;
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody UserDtoForCreating userDto,
                                    BindingResult bindingResult) {

        userValidator.validate(userDto, bindingResult);


        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    bindingResult.getAllErrors().stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage));
        }

        User user = Converter.getUser(userDto);

        registrationService.createNew(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                Converter.getUserDto(user));
    }
}
