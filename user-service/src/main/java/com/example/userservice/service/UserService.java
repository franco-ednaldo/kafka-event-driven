package com.example.userservice.service;

import com.example.userservice.dto.UserDto;

import java.util.List;

public interface UserService {
    Long createUser(UserDto userDto);

    void updateUser(UserDto userDto);

    List<UserDto> retrievAllUser();
}
