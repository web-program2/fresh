package com.example.User_service.service;

import com.example.User_service.dto.UserDto;
import com.example.User_service.jpa.UserEntity;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll();
}
