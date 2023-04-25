package com.adventure.base.service;

import com.adventure.base.model.User;
import com.adventure.base.model.role.Role;
import com.adventure.base.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    @Autowired
    public RegistrationService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public void createNew(User user) {
        user.getRoles().add(Role.USER);
        userRepository.save(user);
    }
}
