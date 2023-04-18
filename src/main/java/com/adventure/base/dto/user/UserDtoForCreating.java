package com.adventure.base.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDtoForCreating {

    @NotBlank(message = "Введите имя пользователя")
    @NotNull(message = "Введите имя пользователя")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
