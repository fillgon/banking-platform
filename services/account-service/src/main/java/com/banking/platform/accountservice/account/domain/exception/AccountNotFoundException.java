package com.banking.platform.accountservice.account.domain.exception;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(Long id){
        super("Conta com o id " + id + " não encontrada!");
    }

}
