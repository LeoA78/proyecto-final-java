package com.spring.app.exceptions;

import lombok.Data;

@Data
public class BadRequestException extends RuntimeException{
    private final Integer code = 404;

    public BadRequestException(String message) {
        super(message);
    }
}
