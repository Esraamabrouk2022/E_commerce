package com.example.E_commerce.service;

import com.example.E_commerce.model.Auth.AuthenticationRequest;
import com.example.E_commerce.model.Auth.AuthenticationResponse;
import com.example.E_commerce.model.Auth.ResetPasswordRequest;
import com.example.E_commerce.model.User.UserRequestDTO;

public interface AuthenticationService {
    AuthenticationResponse register(UserRequestDTO userRequestDTO);
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    AuthenticationResponse resetPassword(ResetPasswordRequest resetPasswordRequest);
}
