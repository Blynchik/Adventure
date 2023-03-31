package com.adventure.base.util;


import com.adventure.base.config.SecurityConfig;
import com.adventure.base.dto.AdventurerDto;
import com.adventure.base.dto.UserDto;
import com.adventure.base.model.Adventurer;
import com.adventure.base.model.User;
import com.adventure.base.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class UserUtil {

    public static User prepareToSave(User user) {
        user.setPassword(SecurityConfig.PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    public static UserDto getDto(User user) {
        ModelMapper modelMapper = new ModelMapper();

        UserDto userDto = modelMapper.map(user, UserDto.class);

        userDto.setAdventurersDto(
                user.getAdventurers().stream()
                        .map(AdventurerUtil::getDto)
                        .collect(Collectors.toList()));

        return userDto;
    }
}
