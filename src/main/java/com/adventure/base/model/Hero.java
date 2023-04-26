package com.adventure.base.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "hero")
public class Hero {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "first_name")
    @NotBlank(message = "Введите имя")
    @NotNull(message = "Введите имя")
    @NotEmpty(message = "Введите имя")
    @Size(min = 1, max = 20, message = "Должно быть меньше 20 символов")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Введите фамилию")
    @NotNull(message = "Введите фамилию")
    @NotEmpty(message = "Введите фамилию")
    @Size(min = 1, max = 50, message = "Должно быть меньше 50 символов")
    private String lastName;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "enable", nullable = false, columnDefinition = "boolean default true")
    @NotNull
    private boolean enable = true;

    public Hero() {
    }

    public Hero(User user, String firstName, String lastName, LocalDateTime createdAt) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
    }

    public Hero(User user, String firstName, String lastName) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return id == hero.id && Objects.equals(user, hero.user) && firstName.equals(hero.firstName) && lastName.equals(hero.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, firstName, lastName);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
