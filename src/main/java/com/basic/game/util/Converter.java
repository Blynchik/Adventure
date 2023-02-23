package com.basic.game.util;

import com.basic.game.dto.UserTo;
import com.basic.game.model.User;

import java.util.Optional;

public class Converter {

    public static UserTo getUserTo(User user){
        UserTo userTo = new UserTo(user.getNickName(), user.getPhoneNumber());
        userTo.setId(user.getId());
        userTo.setRegisteredAt(user.getRegisteredAt());
        return userTo;
    }

    public static User getUser(User user, UserTo userTo){
        user.setEnabled(userTo.isEnabled());
        user.setNickName(userTo.getNickname());
        user.setPhoneNumber(userTo.getPhoneNumber());
        return user;
    }
}
