package com.example.citymanagement.exception;

public class EntityNotDeletedException extends RuntimeException{
    public EntityNotDeletedException(String message, Long id) {
        super(message + id);
    }
}
