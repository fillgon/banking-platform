package com.banking.platform.accountservice.account.application.service;

import com.banking.platform.accountservice.account.domain.Account;
import com.banking.platform.accountservice.account.domain.exception.AccountNotFoundException;
import com.banking.platform.accountservice.account.domain.exception.DuplicateAccountException;
import com.banking.platform.accountservice.account.infrastruture.repository.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Transactional
    public Account deposit(Long id, BigDecimal amount) {
     Account account = findById(id);

     account.setBalance(account.getBalance().add(amount));
        return account;
    }

}
