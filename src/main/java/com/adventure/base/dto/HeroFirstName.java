package com.adventure.base.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class HeroFirstName {

    @NotBlank(message = "Введите имя героя")
    @NotNull(message = "Введите имя героя")
    @Size(min = 1, max = 20, message = "Должно быть меньше 20 символов")
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
