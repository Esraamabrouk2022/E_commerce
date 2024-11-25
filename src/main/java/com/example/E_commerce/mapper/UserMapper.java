package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.User;
import com.example.E_commerce.model.User.UserRequestDTO;
import com.example.E_commerce.model.User.UserResponseDTO;
import org.mapstruct.Mapper;

import java.security.PublicKey;
@Mapper(componentModel = "spring")
public interface UserMapper {
    public User toEntity(UserRequestDTO userRequestDTO);
    public UserResponseDTO toDto(User user);
}
