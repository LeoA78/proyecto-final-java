package com.spring.app.controllers;

import com.spring.app.dtos.response.ErrorResponseDTO;
import com.spring.app.exceptions.BadRequestException;
import com.spring.app.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorResponseDTO> badRequestHandlerException(BadRequestException ex, HttpServletRequest request) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .typeException(ex.getClass()
                        .getSimpleName())
                .code(ex.getCode())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {
            MethodArgumentTypeMismatchException.class,
            DataIntegrityViolationException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<ErrorResponseDTO> invalidDataHandlerException(RuntimeException ex, HttpServletRequest request) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .typeException(ex.getClass()
                        .getSimpleName())
                .code(400)
                .message("Datos ingresados inválidos")
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
