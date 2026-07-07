package com.banking.platform.accountservice.account.interfaces.mapper;

import com.banking.platform.accountservice.account.domain.Account;
import com.banking.platform.accountservice.account.interfaces.dto.AccountResponse;

import java.util.List;

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

    public static List<AccountResponse> toResponseList(List<Account> accounts) {

        return accounts.stream()
                .map(AccountMapper::toResponse)
                .toList();

    }

}
