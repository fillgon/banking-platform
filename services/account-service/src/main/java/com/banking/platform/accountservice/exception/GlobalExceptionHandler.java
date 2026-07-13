package com.banking.platform.accountservice.exception;

import com.banking.platform.accountservice.account.domain.exception.AccountNotFoundException;
import com.banking.platform.accountservice.account.domain.exception.DuplicateAccountException;
import com.banking.platform.accountservice.account.domain.exception.InsufficientBalanceException;
import com.banking.platform.accountservice.account.domain.exception.SameAccountTransferException;
import com.banking.platform.accountservice.exception.dto.ErrorResponse;
import com.banking.platform.accountservice.exception.dto.FieldErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex) {

        List<FieldErrorResponse> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldErrorResponse(
                        error.getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        return new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "VALIDATION_ERROR",
                "Dados da requisição inválidos.",
                errors
        );
    }

    public ErrorResponse handleDuplicateAccount(DuplicateAccountException ex) {
        return new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "ACCOUNT_ALREADY_EXISTS",
                "Já existe uma conta com esse número.",
                null
        );
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInsufficientBalance(InsufficientBalanceException ex) {
        return new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "INSUFFICIENT_BALANCE",
                "Saldo insuficente para realizar o saque.",
                null
        );
    }

    @ExceptionHandler(SameAccountTransferException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleSameAccountTransfer(SameAccountTransferException ex) {
        return new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "SAME_ACCOUNT_TRANSFER",
                "A conta de origem e a conta de destino devem ser diferentes.",
                null
        );
    }

}
