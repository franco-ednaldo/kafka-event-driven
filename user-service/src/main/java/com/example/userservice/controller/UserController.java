package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Long createUser(@RequestBody final UserDto userDto) {
        return this.userService.createUser(userDto);
    }

    @PutMapping
    public void udpdateUser(@RequestBody final UserDto userDto) {
        this.userService.updateUser(userDto);
    }
    @GetMapping
    public List<UserDto> retrieveUser() {
        return this.userService.retrievAllUser();
    }
}
