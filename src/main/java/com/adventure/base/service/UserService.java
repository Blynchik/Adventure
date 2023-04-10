package com.adventure.base.service;

import com.adventure.base.model.Role;
import com.adventure.base.model.User;
import com.adventure.base.repository.UserRepository;
import com.adventure.base.util.exception.EmptyListException;
import com.adventure.base.util.exception.ForbiddenActionException;
import com.adventure.base.util.exception.UserNotFoundException;
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

    public User getOneById(int id) {

        Optional<User> user = userRepository.findById(id);

        user.ifPresent(value -> Hibernate.initialize(value.getHeroes()));

        return user.orElseThrow(UserNotFoundException::new);
    }

    public List<User> getAll() {

        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new EmptyListException();
        }

        return users;
    }

    @Transactional
    public void addRole(int id, Role role) {

        if (userRepository.existsById(id)) {
            userRepository.getReferenceById(id)
                    .getRoles()
                    .add(role);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Transactional
    public void removeRole(int id, Role role) {

        if (!role.equals(Role.USER)) {

            if (userRepository.existsById(id)) {
                userRepository.getReferenceById(id)
                        .getRoles()
                        .remove(role);
            } else {
                throw new UserNotFoundException();
            }

        } else {
            throw new ForbiddenActionException();
        }
    }

    @Transactional
    public void delete(int id) {

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }
    }

    public User getByName(String name) {

        Optional<User> user = userRepository.findByName(name);

        user.ifPresent(value -> value.setHeroes(Collections.emptyList()));

        return user.orElseThrow(UserNotFoundException::new);
    }

    public Optional<User> checkName(String name) {
        return userRepository.findByName(name);
    }
}
