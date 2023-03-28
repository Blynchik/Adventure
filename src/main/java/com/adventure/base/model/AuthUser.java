package com.adventure.base.model;

import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class AuthUser extends org.springframework.security.core.userdetails.User{
    private final User user;

    public AuthUser(@NonNull User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public long id() {
        return user.getId();
    }

    public User getUser() {
        return user;
    }
}
