package com.adventure.base.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotBlank
    @NotNull
    private String name;

    @Column(name = "registered_at", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredAt = new Date();

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "role",
            joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_role"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Adventurer> adventurers;

    public User() {
    }

    public User(User u) {
        this(u.name, u.registeredAt, u.roles);
    }

    public User(String name, Role... roles) {
        this(name, new Date(), Arrays.asList((roles)));
    }

    public User(String name, Date registeredAt, Collection<Role> roles) {
        this.name = name;
        this.registeredAt = registeredAt;
        setRoles(roles);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public List<Adventurer> getAdventurers() {
        return adventurers;
    }

    public void setAdventurers(List<Adventurer> adventurers) {
        this.adventurers = adventurers;
    }
}
