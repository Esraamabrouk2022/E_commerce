package com.example.E_commerce.controller;

import com.example.E_commerce.model.Auth.AuthenticationRequest;
import com.example.E_commerce.model.Auth.AuthenticationResponse;
import com.example.E_commerce.model.User.UserRequestDTO;
import com.example.E_commerce.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestParam UserRequestDTO userRequestDTO) {
        AuthenticationResponse authenticationResponse = authenticationService.register(userRequestDTO);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/authanticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestParam AuthenticationRequest authenticationRequest){
        AuthenticationResponse authenticationResponse=authenticationService.authenticate(authenticationRequest);
        return ResponseEntity.ok(authenticationResponse);
    }

}
