package com.example.citymanagement.exception;

public class EntityNotUpdatedException extends RuntimeException{
    public EntityNotUpdatedException(String message, Long id) {
        super(message + id);
    }
}
