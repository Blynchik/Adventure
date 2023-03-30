package com.adventure.base.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "email")
    @NotBlank(message = "Введите электронную почту")
    @NotNull(message = "Введите электронную почту")
    @Size(max = 100, message = "Должно быть меньше 100 символов")
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Введите пароль")
    @NotNull(message = "Введите пароль")
    @Size(min = 8, max = 100, message = "Пароль должен быть не меньше меньше 8 знаков")
    private String password;

    @Column(name = "registered_at", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
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

    public User() {
    }

    public User(User u) {
        this(u.email, u.password, u.registeredAt, u.roles);
    }

    public User(String email, String password, Role... roles) {
        this(email, password, new Date(), Arrays.asList((roles)));
    }

    public User(String email, String password, Date registeredAt, Collection<Role> roles) {
        this.email = email;
        this.password = password;
        this.registeredAt = registeredAt;
        setRoles(roles);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
