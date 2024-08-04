package com.example.citymanagement.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message, Long id) {
        super(message + id);
    }
}
