package com.example.E_commerce.mapper.Impl;

import com.example.E_commerce.entity.User;
import com.example.E_commerce.mapper.UserMapper;
import com.example.E_commerce.model.User.UserRequestDTO;
import com.example.E_commerce.model.User.UserResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User toEntity(UserRequestDTO userRequestDTO) {
        if(userRequestDTO==null){
            return null;
        }
        User user=new User();
        user.setUserRole(userRequestDTO.getRole());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        return user;
    }

    @Override
    public UserResponseDTO toDto(User user) {
        if(user==null){
            return null;
        }
        UserResponseDTO userResponseDTO=new UserResponseDTO();
        userResponseDTO.setUserName(user.getFirstName()+user.getLastName());
        userResponseDTO.setId(user.getId());
        userResponseDTO.setEmail(user.getEmail());
        return userResponseDTO;
    }
}
