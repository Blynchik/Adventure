package com.adventure.base.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

import java.util.Date;

@Entity
@Table(name = "adventurer")
public class Adventurer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
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

    @Column(name = "strength")
    @NotNull(message = "Введите значение")
    private int strength;

    @Column(name = "agility")
    @NotNull(message = "Введите значение")
    private int agility;

    @Column(name = "constitution")
    @NotNull(message = "Введите значение")
    private int constitution;

    @Column(name = "intelligence")
    @NotNull(message = "Введите значение")
    private int intelligence;

    @Column(name = "wisdom")
    @NotNull(message = "Введите значение")
    private int wisdom;

    @Column(name = "charisma")
    @NotNull(message = "Введите значение")
    private int charisma;

    @Column(name = "evil_good")
    @NotNull(message = "Введите значение")
    @Range(min = 0, max = 150)
    private int evilGood;

    @Column(name = "chaotic_lawful")
    @NotNull(message = "Введите значение")
    @Range(min = 0, max = 150)
    private int chaoticLawful;

    @Column(name = "money")
    @NotNull(message = "Введите значение")
    private long money;

    @Column(name = "playable", nullable = false, columnDefinition = "bool default true")
    @NotNull
    private boolean playable;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    private Date createdAt = new Date();

    public Adventurer() {
    }

    public Adventurer(User user, String firstName, String lastName,
                      int strength, int agility, int constitution,
                      int intelligence, int wisdom, int charisma,
                      int evilGood, int chaoticLawful, long money,
                      boolean playable, Date createdAt) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.strength = strength;
        this.agility = agility;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.evilGood = evilGood;
        this.chaoticLawful = chaoticLawful;
        this.money = money;
        this.playable = playable;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getEvilGood() {
        return evilGood;
    }

    public void setEvilGood(int evilGood) {
        this.evilGood = evilGood;
    }

    public int getChaoticLawful() {
        return chaoticLawful;
    }

    public void setChaoticLawful(int chaoticLawful) {
        this.chaoticLawful = chaoticLawful;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
