package com.example.E_commerce.service;

import com.example.E_commerce.model.Auth.AuthenticationRequest;
import com.example.E_commerce.model.Auth.AuthenticationResponse;
import com.example.E_commerce.model.User.UserRequestDTO;

public interface AuthenticationService {
    public AuthenticationResponse register(UserRequestDTO userRequestDTO);
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
