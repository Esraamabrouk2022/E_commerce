package com.example.E_commerce.service.impl;


import com.example.E_commerce.Security.JwtService;
import com.example.E_commerce.entity.Enum.TokenType;
import com.example.E_commerce.entity.Token;
import com.example.E_commerce.entity.User;
import com.example.E_commerce.exception.AuthException;
import com.example.E_commerce.mapper.UserMapper;
import com.example.E_commerce.model.Auth.AuthenticationRequest;
import com.example.E_commerce.model.Auth.AuthenticationResponse;
import com.example.E_commerce.model.Auth.ResetPasswordRequest;
import com.example.E_commerce.model.User.UserRequestDTO;
import com.example.E_commerce.repository.TokenRepository;
import com.example.E_commerce.repository.UserRepository;
import com.example.E_commerce.service.AuthenticationService;
import com.example.E_commerce.service.EmailService;
import com.example.E_commerce.service.TokenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImp implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final TokenService tokenService;
    private final TokenRepository tokenRepository;

    @Override
    public AuthenticationResponse register(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        User user = userMapper.toEntity(userRequestDTO);
        log.info("Mapped User: " + user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while registering user", e);
        }

        var jwtToken = jwtService.generateToken(user);

        String emailContent = "<div style='font-family: Arial, sans-serif; width: 80%; margin: auto; padding: 20px; border: 1px solid #ddd; border-radius: 5px;'>"
                + "<div style='text-align: center; padding: 10px; background-color: #f8f8f8; border-bottom: 1px solid #ddd;'>"
                + "<h1>Welcome to E-commerce website</h1>"
                + "</div>"
                + "<div style='padding: 20px;'>"
                + "<p>Dear " + user.getUsername() + ",</p>"
                + "<p>Thank you for registering at My e-commerce website. Please click the link below to verify your email:</p>"
                + "<p><a href='http://localhost:7070/auth/verify-email?token=" + jwtToken + "'>Verify Email</a></p>"
                + "<p>If you did not register at  My e-commerce website, please ignore this email.</p>"
                + "<p>Best Regards,</p>"
                + "<p>The XJudge Team</p>"
                + "</div>"
                + "</div>";

        emailService.send(user.getEmail() , "Email Verification" , emailContent);

        tokenService.save(Token.builder()
                .token(jwtToken)
                .user(user)
                .tokenType(TokenType.EMAIL_VERIFICATION)
                .expiredAt(LocalDateTime.now().plusHours(24))
                .verifiedAt(null)
                .build());



        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .userResponseDTO(userMapper.toDto(user))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userResponseDTO(userMapper.toDto(user))
                .build();
    }

    @Override
    @Transactional
    public AuthenticationResponse resetPassword(ResetPasswordRequest resetPasswordRequest) {
        User user = userRepository.findByEmail(resetPasswordRequest.getEmail())
                .orElseThrow(() -> new AuthException("Email not found", HttpStatus.NOT_FOUND));

        String token = UUID.randomUUID().toString();

        Token passwordResetToken = new Token();
        passwordResetToken.setToken(token);
        passwordResetToken.setUser(user);
        passwordResetToken.setTokenType(TokenType.PASSWORD_RESET);
        passwordResetToken.setExpiredAt(LocalDateTime.now().plusHours(24));
        Token savedToken= tokenRepository.save(passwordResetToken);

        String resetUrl = "http://localhost:8082/auth/reset-password?token=" + token;
        String emailContent = "<div style='font-family: Arial, sans-serif;'>"
                + "<h1>Password Reset Request</h1>"
                + "<p>Dear " + user.getUsername() + ",</p>"
                + "<p>Click the button below to reset your password:</p>"
                + "<p><a href='" + resetUrl + "' style='padding: 10px 20px; background-color: #4CAF50; color: white; text-decoration: none;'>Reset Password</a></p>"
                + "<p>If you did not request a password reset, please ignore this email.</p>"
                + "</div>";
        emailService.send(user.getEmail(), "Reset Your Password", emailContent);
        return new AuthenticationResponse(savedToken.getToken(),userMapper.toDto(user));
    }
}
