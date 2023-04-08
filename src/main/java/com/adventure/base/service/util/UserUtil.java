package com.adventure.base.service.util;


import com.adventure.base.dto.UserDto;
import com.adventure.base.model.User;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;

public class UserUtil {

    public static User prepareToSave(UserDto userDto) {
//        user.setPassword(SecurityConfig.PASSWORD_ENCODER.encode(user.getPassword()));
        User user = new User();
        user.setName(userDto.getName());
        return user;
    }

//    public static UserDto getDto(User user) {
//        ModelMapper modelMapper = new ModelMapper();
//
//        UserDto userDto = modelMapper.map(user, UserDto.class);
//
//        userDto.setAdventurersDto(
//                user.getAdventurers().stream()
//                        .map(AdventurerUtil::getDto)
//                        .collect(Collectors.toList()));
//
//        return userDto;
//    }
}
