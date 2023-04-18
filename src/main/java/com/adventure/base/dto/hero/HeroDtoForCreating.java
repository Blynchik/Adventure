package com.adventure.base.dto.hero;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class HeroDtoForCreating {

    @NotBlank(message = "Введите имя героя")
    @NotNull(message = "Введите имя героя")
    @Size(min = 1, max = 20, message = "Должно быть меньше 20 символов")
    private String firstName;

    @NotBlank(message = "Введите фамилию героя")
    @NotNull(message = "Введите фамилию героя")
    @Size(min = 1, max = 50, message = "Должно быть меньше 50 символов")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
