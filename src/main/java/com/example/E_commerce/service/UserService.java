package com.example.E_commerce.service;

import com.example.E_commerce.model.User.UserRequestDTO;
import com.example.E_commerce.model.User.UserResponseDTO;

import java.util.List;


public interface UserService {

   public UserResponseDTO getUserById(Long id);
   public UserResponseDTO findByEmail(String Email);
   public List<UserResponseDTO> getAllUsers();
   public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
   public void deleteUser(Long id);
}
