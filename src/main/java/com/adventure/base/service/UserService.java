package com.adventure.base.service;

import com.adventure.base.model.Role;
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
    public void createNew(User user) {
        user.getRoles().add(Role.USER);
        userRepository.save(user);
    }

    public Optional<User> getOneById(int id) {

        Optional<User> user = userRepository.findById(id);
        user.ifPresent(value -> Hibernate.initialize(value.getAdventurers()));

        return user;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void addRole(int id, Role role) {

        if (userRepository.existsById(id)) {
            userRepository.getReferenceById(id)
                    .getRoles()
                    .add(role);
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
