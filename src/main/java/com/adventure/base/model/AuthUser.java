package com.adventure.base.model;

import org.springframework.lang.NonNull;

public class AuthUser extends org.springframework.security.core.userdetails.User{
    private final User user;

    public AuthUser(@NonNull User user) {
        super(user.getName(), "{noop}password", user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.getId();
    }

    public User getUser() {
        return user;
    }
}
