package com.adventure.base.util.validator;

import com.adventure.base.dto.user.UserDtoForCreating;
import com.adventure.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDtoForCreating.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDtoForCreating user = (UserDtoForCreating) target;

        if (userService.nameExistence(user.getName())) {
            errors.rejectValue("name", "", "Имя " + user.getName() + " уже зарегистрировано");
        }
    }
}
