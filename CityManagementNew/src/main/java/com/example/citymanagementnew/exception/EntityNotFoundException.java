package com.example.citymanagementnew.exception;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message, Long id) {
        super(message + id);
    }
}
