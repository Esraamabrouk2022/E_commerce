package com.example.E_commerce.controller;

import com.example.E_commerce.model.Auth.AuthenticationRequest;
import com.example.E_commerce.model.Auth.AuthenticationResponse;
import com.example.E_commerce.model.Auth.ResetPasswordRequest;
import com.example.E_commerce.model.Response;
import com.example.E_commerce.model.User.UserRequestDTO;
import com.example.E_commerce.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor

public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        AuthenticationResponse authenticationResponse = authenticationService.register(userRequestDTO);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/authanticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        AuthenticationResponse authenticationResponse=authenticationService.authenticate(authenticationRequest);
        return ResponseEntity.ok(authenticationResponse);
    }
    @PostMapping("/reset-password")
    AuthenticationResponse resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest){
       return authenticationService.resetPassword(resetPasswordRequest);

    }

}
