package com.adventure.base.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "hero")
public class Hero {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "first_name")
    @NotBlank(message = "Введите имя")
    @NotNull(message = "Введите имя")
    @Size(min = 1, max = 20, message = "Должно быть меньше 20 символов")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Введите фамилию")
    @NotNull(message = "Введите фамилию")
    @Size(min = 1, max = 50, message = "Должно быть меньше 50 символов")
    private String lastName;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    private Date createdAt = new Date();

    public Hero() {
    }

    public Hero(User user, String firstName, String lastName, Date createdAt) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
