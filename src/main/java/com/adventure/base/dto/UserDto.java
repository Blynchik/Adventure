package com.adventure.base.dto;

import com.adventure.base.model.role.Role;

import java.util.Date;
import java.util.Set;

public class UserDto extends  UserDtoForCreating{

    private int id;

    private Set<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
