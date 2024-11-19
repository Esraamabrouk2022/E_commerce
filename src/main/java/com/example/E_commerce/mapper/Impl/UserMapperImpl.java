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
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setUserRole(userRequestDTO.getRole());
        return user;
    }

    @Override
    public UserResponseDTO toDto(User user) {
        UserResponseDTO userResponseDTO=new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setUserName(user.getFirstName()+" "+user.getLastName());

        return userResponseDTO;
    }
}
