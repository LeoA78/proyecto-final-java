package com.spring.app.controllers;

import com.spring.app.dtos.response.ErrorResponseDTO;
import com.spring.app.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> runtimeHandlerException(RuntimeException ex, HttpServletRequest request) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .typeException(ex.getClass()
                        .getSimpleName())
                .code(444)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorResponseDTO> notFoundHandlerException(Exception ex, HttpServletRequest request) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .typeException(ex.getClass()
                        .getSimpleName())
                .code(404)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
