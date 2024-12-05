package com.example.E_commerce.service;

import com.example.E_commerce.entity.Token;

public interface TokenService {
    void save(Token token);
    Token findByToken(String token);
}
