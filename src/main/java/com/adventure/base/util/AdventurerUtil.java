package com.adventure.base.util;

import com.adventure.base.dto.AdventurerDto;
import com.adventure.base.dto.UserDto;
import com.adventure.base.model.Adventurer;
import com.adventure.base.model.User;
import org.modelmapper.ModelMapper;

public class AdventurerUtil {
    public static AdventurerDto getDto(Adventurer adventurer) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(adventurer, AdventurerDto.class);
    }
}