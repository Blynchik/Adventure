package com.basic.game.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class UserTo {
    @NotBlank(message = "nickName не должно быть пустым")
    @Size(min = 2, max = 100, message = "Должно быть больше 1 и меньше 100 символов")
    private String nickname;
    @NotBlank(message = "phoneNumber не должно быть пустым")
    @Size(min = 12, max = 13, message = "Должно быть 12 знаков")
    private String phoneNumber;


    public UserTo(){}

    public UserTo(String nickname, String phoneNumber){
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
