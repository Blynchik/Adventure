package com.basic.game.util;

import com.basic.game.dto.UserTo;
import com.basic.game.model.User;


public class Converter {

    public static UserTo getUserTo(User user){
        return new UserTo(user.getNickName(), user.getPhoneNumber());
    }

    public static User getUser(User user, UserTo userTo){
        user.setNickName(userTo.getNickname());
        user.setPhoneNumber(userTo.getPhoneNumber());
        return user;
    }
}
