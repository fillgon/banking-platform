package com.banking.platform.accountservice.account.domain.exception;

public class SameAccountTransferException extends RuntimeException {
    public SameAccountTransferException() {
        super("As contas de origem e destino devem ser diferentes.");
    }
}
