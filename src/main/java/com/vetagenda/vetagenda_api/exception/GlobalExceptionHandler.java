package com.vetagenda.vetagenda_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Erro 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandartError> resourceNotFoundException (ResourceNotFoundException e) {
        StandartError error = new StandartError();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setErro("Resource not found");
        error.setMensagem(e.getMessage());
        error.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Erro de conflito de horários
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandartError> ConflictException (ConflictException e) {
        StandartError error = new StandartError();

        error.setStatus(HttpStatus.CONFLICT.value());
        error.setErro("Resource not found");
        error.setMensagem(e.getMessage());
        error.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
