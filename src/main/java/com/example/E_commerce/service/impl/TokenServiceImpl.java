package com.example.E_commerce.service.impl;

import com.example.E_commerce.entity.Token;
import com.example.E_commerce.exception.ResourceNotFoundException;
import com.example.E_commerce.repository.TokenRepository;
import com.example.E_commerce.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;

    @Override
    public void save(Token token) {
        tokenRepository.save(token);
    }
    @Override
    public Token findByToken(String token) {
        return tokenRepository.findByToken(token).orElseThrow(
                () -> new ResourceNotFoundException("Invalid token")
        );
    }

}
