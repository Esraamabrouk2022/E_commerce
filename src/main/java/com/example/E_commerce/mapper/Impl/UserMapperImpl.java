package com.example.E_commerce.mapper.Impl;

import com.example.E_commerce.entity.Enum.User_Role;
import com.example.E_commerce.entity.User;
import com.example.E_commerce.mapper.UserMapper;
import com.example.E_commerce.model.User.UserRequestDTO;
import com.example.E_commerce.model.User.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toEntity(UserRequestDTO userRequestDTO) {
        User user=new User();
        user.setName(userRequestDTO.getUserName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setUserRole(User_Role.User);
        return user;
    }

    @Override
    public UserResponseDTO toDto(User user) {
        UserResponseDTO userResponseDTO=new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setUserName(user.getName());

        return userResponseDTO;
    }
}
