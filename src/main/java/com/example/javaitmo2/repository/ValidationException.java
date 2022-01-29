package com.example.javaitmo2.repository;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
