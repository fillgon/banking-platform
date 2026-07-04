package com.banking.platform.accountservice.account.application.service;

import com.banking.platform.accountservice.account.domain.Account;
import com.banking.platform.accountservice.account.domain.exception.AccountNotFoundException;
import com.banking.platform.accountservice.account.infrastruture.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String ownerName) {

        Account account = Account.builder()
                .accountNumber(generateAccountNumber())
                .ownerName(ownerName)
                .balance(BigDecimal.ZERO)
                .createdAt(LocalDateTime.now())
                .build();

        return accountRepository.save(account);
    }

    private String generateAccountNumber() {
        return String.valueOf(System.currentTimeMillis());
    }

    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

}
