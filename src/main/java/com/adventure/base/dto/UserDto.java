package com.adventure.base.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class UserDto {

    @NotBlank(message = "Введите электронную почту")
    @NotNull(message = "Введите электронную почту")
    @Size(max = 100, message = "Должно быть меньше 100 символов")
    @Email
    private String email;

    private List<AdventurerDto> adventurersDto;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AdventurerDto> getAdventurersDto() {
        return adventurersDto;
    }

    public void setAdventurersDto(List<AdventurerDto> adventurersDto) {
        this.adventurersDto = adventurersDto;
    }
}
