package com.adventure.base.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class HeroLastName {

    @NotBlank(message = "Введите фамилию")
    @NotNull(message = "Введите фамилию")
    @Size(min = 1, max = 50, message = "Должно быть меньше 50 символов")
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
