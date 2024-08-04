package com.example.citymanagement.exception;

public class EntityNotCreatedException extends RuntimeException{
    public EntityNotCreatedException(String message) {
        super(message);
    }
}
