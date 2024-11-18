package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Enum.User_Role;
import com.example.E_commerce.entity.User;
import com.example.E_commerce.mapper.UserMapper;
import com.example.E_commerce.model.User.UserRequestDTO;
import com.example.E_commerce.model.User.UserResponseDTO;
import com.example.E_commerce.repository.UserRepository;
import com.example.E_commerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " not found"));
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDTO findByEmail(String email) {
        User user=userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User with email "+email+" not found "));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users=userRepository.findAll();
        return users
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user=userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User with id "+id+" Not found"));
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setUserRole(User_Role.User);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteUser(Long id) {
     if(!userRepository.existsById(id)){
         throw new UsernameNotFoundException("User with id "+ id +" not found");
     }
     userRepository.deleteById(id);
    }
}
