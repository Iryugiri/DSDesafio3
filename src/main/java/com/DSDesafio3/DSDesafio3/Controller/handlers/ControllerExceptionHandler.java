package com.DSDesafio3.DSDesafio3.Controller.handlers;

import com.DSDesafio3.DSDesafio3.DTO.CustomError;
import com.DSDesafio3.DSDesafio3.DTO.ValidationError;
import com.DSDesafio3.DSDesafio3.Service.exception.DSException;
import com.DSDesafio3.DSDesafio3.Service.exception.DeleteUserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DSException.class)
    public ResponseEntity<CustomError> dsComerse(DSException dsException, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(), status.value(),dsException.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError(Instant.now(), status.value(), "Ocorreu um erro na Validacao", request.getRequestURI());
        for (FieldError fieldError: e.getBindingResult().getFieldErrors()) {
            err.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(), status.value(), "Id nao encontrado" ,request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DeleteUserNotFoundException.class)
    public ResponseEntity<CustomError> deleteUserNotFound(DeleteUserNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage() ,request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
