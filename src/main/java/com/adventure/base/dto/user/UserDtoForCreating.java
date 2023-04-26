package com.adventure.base.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDtoForCreating {

    @NotBlank(message = "Введите имя пользователя")
    @NotNull(message = "Введите имя пользователя")
    @Size(min = 2, message = "Минимум 2 символа")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
