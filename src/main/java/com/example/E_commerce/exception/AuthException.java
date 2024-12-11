package com.example.E_commerce.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class AuthException extends RuntimeException {
    private final HttpStatus statusCode;

    public AuthException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;

    }
}
