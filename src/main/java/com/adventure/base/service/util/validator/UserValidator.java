package com.adventure.base.service.util.validator;

import com.adventure.base.model.User;
import com.adventure.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (userService.getByName(user.getName()).isPresent()){
            errors.rejectValue("name", "", "Уже существует");
        }
    }
}
