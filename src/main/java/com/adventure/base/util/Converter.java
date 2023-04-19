package com.adventure.base.util;

import com.adventure.base.dto.hero.HeroDto;
import com.adventure.base.dto.user.UserDto;
import com.adventure.base.dto.user.UserDtoForCreating;
import com.adventure.base.model.Hero;
import com.adventure.base.model.User;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;

public class Converter {

    public static User getUser(UserDtoForCreating userDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDto, User.class);
    }

    public static UserDto getUserDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(user, UserDto.class);

        userDto.setRegistered_at(user.getRegisteredAt());

        return userDto;
    }

    public static UserDto getEnrichedUserDto(User user){
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(user, UserDto.class);

        userDto.setRegistered_at(user.getRegisteredAt());
        userDto.setHeroesId(user.getHeroes().stream()
                .map(Hero::getId)
                .collect(Collectors.toList()));

        return userDto;
    }

    public static HeroDto getHeroDto(Hero hero) {
        ModelMapper modelMapper = new ModelMapper();
        HeroDto heroDto = modelMapper.map(hero, HeroDto.class);

        heroDto.setUser_id(hero.getUser().getId());

        return heroDto;
    }
}
