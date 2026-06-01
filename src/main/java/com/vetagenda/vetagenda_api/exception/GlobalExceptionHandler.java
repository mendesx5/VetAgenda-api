package com.vetagenda.vetagenda_api.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.ResponseEntity.status;

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

        return status(HttpStatus.NOT_FOUND).body(error);
    }

    // Erro de conflito de horários (409)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandartError> conflictException (ConflictException e) {
        StandartError error = new StandartError();

        error.setStatus(HttpStatus.CONFLICT.value());
        error.setErro("Conflito de horário");
        error.setMensagem(e.getMessage());
        error.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // Erro de validação de campos (400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleMethodArgumentNotValidException (MethodArgumentNotValidException e) {
        ValidationError err = new ValidationError();
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setErro("Erro de validação");
        err.setTimestamp(LocalDateTime.now());

        e.getBindingResult().getFieldErrors().forEach(f -> {
            err.addError(f.getField(), f.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
