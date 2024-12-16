package com.example.citymanagementnew.exceptionHandler;

import com.example.citymanagementnew.exception.EntityNotDeletedException;
import com.example.citymanagementnew.exception.EntityNotFoundException;
import com.example.citymanagementnew.exception.EntityNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EntityInternalServerErrorExceptionHandler {

    @ExceptionHandler({EntityNotCreatedException.class, EntityNotFoundException.class, EntityNotDeletedException.class})
    public ResponseEntity<String> handleEntityInternalServerErrorException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
