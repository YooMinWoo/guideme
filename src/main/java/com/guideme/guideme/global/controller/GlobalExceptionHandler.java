package com.guideme.guideme.global.controller;

import com.guideme.guideme.global.dto.ApiResponse;
import com.guideme.guideme.global.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customExceptionHandler(CustomException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(ApiResponse.fail(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> ExceptionHandler(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(ApiResponse.fail(e.getMessage()));
    }
}
