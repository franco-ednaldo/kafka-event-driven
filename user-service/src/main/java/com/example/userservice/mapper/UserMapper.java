package com.example.userservice.mapper;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    UserDto toDto(User user);
}
