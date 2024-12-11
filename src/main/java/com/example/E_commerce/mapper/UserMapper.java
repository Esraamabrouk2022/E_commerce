package com.example.E_commerce.mapper;

import com.example.E_commerce.entity.User;
import com.example.E_commerce.model.User.UserRequestDTO;
import com.example.E_commerce.model.User.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.security.PublicKey;

public interface UserMapper {

     User toEntity(UserRequestDTO userRequestDTO);
     UserResponseDTO toDto(User user);
}
