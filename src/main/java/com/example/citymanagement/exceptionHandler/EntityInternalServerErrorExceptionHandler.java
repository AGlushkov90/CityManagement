package com.example.citymanagement.exceptionHandler;

import com.example.citymanagement.exception.EntityNotDeletedException;
import com.example.citymanagement.exception.EntityNotFoundException;
import com.example.citymanagement.exception.EntityNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EntityInternalServerErrorExceptionHandler {

    @ExceptionHandler({EntityNotCreatedException.class, EntityNotFoundException.class, EntityNotDeletedException.class})
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
