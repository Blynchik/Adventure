package com.adventure.base.dto.user;

import com.adventure.base.model.role.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class UserDto extends UserDtoForCreating {

    private Integer id;

    private Set<Role> roles;

    private LocalDateTime registered_at;

    private List<Integer> heroesId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public LocalDateTime getRegistered_at() {
        return registered_at;
    }

    public void setRegistered_at(LocalDateTime registered_at) {
        this.registered_at = registered_at;
    }

    public List<Integer> getHeroesId() {
        return heroesId;
    }

    public void setHeroesId(List<Integer> heroesId) {
        this.heroesId = heroesId;
    }
}
