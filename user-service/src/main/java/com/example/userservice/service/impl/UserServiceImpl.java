package com.example.userservice.service.impl;

import com.example.userservice.dto.UserDto;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final KafkaTemplate<Long, String> kafkaTemplate;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
                           KafkaTemplate<Long, String> kafkaTemplate) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Long createUser(UserDto userDto) {
        final var user = this.userMapper.toEntity(userDto);
        return this.userRepository.save(user).getId();
    }

    @Override
    @Transactional
    public void updateUser(final UserDto userDto) {
        this.userRepository.findById(userDto.getId()).ifPresent(user -> {
            final var userUpdated = this.userMapper.toDto(user);
            this.raiseEvent(userUpdated);
        });
    }

    @Override
    public List<UserDto> retrievAllUser() {
        return this.userRepository.findAll().stream()
                .map(user ->this.userMapper.toDto(user))
                .collect(Collectors.toList());
    }

    private void raiseEvent(final UserDto userDto) {
        try {
            final var value = OBJECT_MAPPER.writeValueAsString(userDto);
            this.kafkaTemplate.sendDefault(userDto.getId(), value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
