package com.example.rolemanagement.config;

import com.example.roleframework.dto.ErrorResponse;
import com.example.roleframework.exception.ResourceNotFoundException;
import com.example.roleframework.exception.InvalidUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse("NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Handle InvalidUserException
    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ErrorResponse> handleInvalidUser(InvalidUserException ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse("INVALID_USER", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse("INTERNAL_ERROR", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}