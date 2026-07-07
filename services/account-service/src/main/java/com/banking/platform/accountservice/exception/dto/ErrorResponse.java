package com.banking.platform.accountservice.exception.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(

        LocalDateTime timestamp,
        Integer status,
        String code,
        String message,
        List<FieldErrorResponse> errors
) {
}
