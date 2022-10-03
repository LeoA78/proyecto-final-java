package com.spring.app.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDTO {
    private String typeException;
    private Integer code;
    private String message;
    private String path;
}
