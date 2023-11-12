package com.elderbr.clients.controllers.handlers;

import com.elderbr.clients.dto.CustomError;
import com.elderbr.clients.dto.ValidationError;
import com.elderbr.clients.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException erro, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now(), status.value(), erro.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(customError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> argumentNotValidException(MethodArgumentNotValidException erro, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError validationError = new ValidationError(Instant.now(), status.value(), "Dados inválidos!", request.getRequestURI());
        for(FieldError fieldError : erro.getBindingResult().getFieldErrors()){
            validationError.add(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(validationError);
    }

}
