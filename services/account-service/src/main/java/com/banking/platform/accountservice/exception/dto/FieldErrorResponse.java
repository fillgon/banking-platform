package com.banking.platform.accountservice.exception.dto;

public record FieldErrorResponse(

        String field,
        String message

) {
}
