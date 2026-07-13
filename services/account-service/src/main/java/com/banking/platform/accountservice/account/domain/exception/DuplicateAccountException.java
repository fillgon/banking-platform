package com.banking.platform.accountservice.account.domain.exception;

public class DuplicateAccountException extends RuntimeException {

    public DuplicateAccountException(String accountNumber) {
        super("Número da conta: " + accountNumber + " já existe!");
    }
}
