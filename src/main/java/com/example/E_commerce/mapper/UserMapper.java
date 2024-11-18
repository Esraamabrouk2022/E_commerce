package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.User;
import com.example.E_commerce.model.User.UserRequestDTO;
import com.example.E_commerce.model.User.UserResponseDTO;

import java.security.PublicKey;

public interface UserMapper {
    public User toEntity(UserRequestDTO userRequestDTO);
    public UserResponseDTO toDto(User user);
}
