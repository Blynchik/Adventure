package com.adventure.base.util;

import com.adventure.base.dto.user.UserDto;
import com.adventure.base.dto.user.UserDtoForCreating;
import com.adventure.base.model.User;
import org.modelmapper.ModelMapper;

public class Converter {

    public static User getUser(UserDtoForCreating userDto){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDto, User.class);
    }

    public static UserDto getUserDto(User user){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);

    }
}
