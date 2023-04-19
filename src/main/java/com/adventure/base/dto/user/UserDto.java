package com.adventure.base.dto.user;

import com.adventure.base.model.role.Role;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class UserDto extends UserDtoForCreating {

    private int id;

    private Set<Role> roles;

    private Date registered_at;

    private List<Integer> heroesId;

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

    public Date getRegistered_at() {
        return registered_at;
    }

    public void setRegistered_at(Date registered_at) {
        this.registered_at = registered_at;
    }

    public List<Integer> getHeroesId() {
        return heroesId;
    }

    public void setHeroesId(List<Integer> heroesId) {
        this.heroesId = heroesId;
    }
}
