package com.spring.app.exceptions.customsExceptions;

import lombok.*;

@Data
public class NotFoundException extends RuntimeException{
    private final Integer code = 404;

    public NotFoundException(String message) {
        super(message);
    }
}
