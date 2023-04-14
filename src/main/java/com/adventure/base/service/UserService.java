package com.adventure.base.service;

import com.adventure.base.model.role.Role;
import com.adventure.base.model.User;
import com.adventure.base.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void createNew(User user) {
        user.getRoles().add(Role.USER);
        userRepository.save(user);
    }

    public Optional<User> getOneById(int id) {

        Optional<User> user = userRepository.findById(id);
        user.ifPresent(u -> Hibernate.initialize(user.get().getHeroes()));

        return user;
    }

    public List<User> getAll() {

        List<User> users = userRepository.findAll();

        for (User user : users) {
            user.setHeroes(Collections.emptyList());
        }

        return users;
    }

    @Transactional
    public void addRole(int id, Role role) {
        User user = userRepository.getReferenceById(id);
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Transactional
    public void removeRole(int id, Role role) {
        User user = userRepository.getReferenceById(id);
        user.getRoles().remove(role);
        userRepository.save(user);
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public Optional<User> getByName(String name) {

        Optional<User> user = userRepository.findByName(name);
        user.ifPresent(u -> u.setHeroes(Collections.emptyList()));

        return user;
    }

    public Optional<User> checkName(String name) {
        return userRepository.findByName(name);
    }

    public boolean checkExistence(int id) {
        return userRepository.existsById(id);
    }
}
