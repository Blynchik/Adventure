package com.adventure.base.service;

import com.adventure.base.model.User;
import com.adventure.base.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void create(User user) {
        userRepository.save(user);
    }

    public Optional<User> getUserById(int id) {
        Hibernate.initialize(userRepository.findById(id).get().getAdventurers());
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        users.forEach(u->Hibernate.initialize(u.getAdventurers()));

        return users;
    }

    @Transactional
    public void update(int id, User updatedUser) {

        if (userRepository.existsById(id)) {
            updatedUser.setId(id);
            userRepository.save((updatedUser));
        }
    }

    @Transactional
    public void delete(int id) {

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    public Optional<User> getByName(String name) {
        return userRepository.findByName(name);
    }
}
