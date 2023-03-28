package com.adventure.base.util;


import com.adventure.base.config.SecurityConfig;
import com.adventure.base.model.User;
import com.adventure.base.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserUtil {

    public static User prepareToSave(User user){
        user.setPassword(SecurityConfig.PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
