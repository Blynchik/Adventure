package com.adventure.base.dto;

import com.adventure.base.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public class AdventurerDto {

    private UserDto user;

    @NotBlank(message = "Введите имя")
    @NotNull(message = "Введите имя")
    @Size(min = 1, max = 20, message = "Должно быть меньше 20 символов")
    private String firstName;

    @NotBlank(message = "Введите фамилию")
    @NotNull(message = "Введите фамилию")
    @Size(min = 1, max = 50, message = "Должно быть меньше 50 символов")
    private String lastName;

    @NotNull(message = "Введите значение")
    private int strength;

    @NotNull(message = "Введите значение")
    private int agility;

    @NotNull(message = "Введите значение")
    private int constitution;

    @NotNull(message = "Введите значение")
    private int intelligence;

    @NotNull(message = "Введите значение")
    private int wisdom;

    @NotNull(message = "Введите значение")
    private int charisma;

    @NotNull(message = "Введите значение")
    @Range(min = 0, max = 150)
    private int evilGood;

    @NotNull(message = "Введите значение")
    @Range(min = 0, max = 150)
    private int chaoticLawful;

    @NotNull(message = "Введите значение")
    private long money;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
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
}
