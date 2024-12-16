package com.example.citymanagementnew.exception;

public class EntityNotUpdatedException extends RuntimeException{
    public EntityNotUpdatedException(String message, Long id) {
        super(message + id);
    }
}
