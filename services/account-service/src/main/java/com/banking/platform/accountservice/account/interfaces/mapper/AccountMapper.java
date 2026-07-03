package com.banking.platform.accountservice.account.interfaces.mapper;

import com.banking.platform.accountservice.account.domain.Account;
import com.banking.platform.accountservice.account.interfaces.dto.AccountResponse;

public class AccountMapper {

    private AccountMapper(){}

    public static AccountResponse toResponse(Account account){

        return new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getOwnerName(),
                account.getBalance(),
                account.getCreatedAt()
        );

    }

}
