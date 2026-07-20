package com.banking.platform.accountservice.account.domain.exception;

public class InvalidStatementPeriodException extends RuntimeException {

    public InvalidStatementPeriodException() {
        super("Os parâmetros from e to devem ser informados juntos.");
    }
}
