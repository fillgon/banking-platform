package com.banking.platform.accountservice.account.interfaces.controller;

import com.banking.platform.accountservice.account.application.service.AccountService;
import com.banking.platform.accountservice.account.domain.Account;
import com.banking.platform.accountservice.account.interfaces.dto.*;
import com.banking.platform.accountservice.account.interfaces.mapper.AccountMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RestControllerAdvice
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse create(@Valid @RequestBody CreateAccountRequest request) {

        Account account = accountService.createAccount(request.ownerName());

        return AccountMapper.toResponse(account);
    }

    @GetMapping("/{id}")
    public AccountResponse findById(@PathVariable Long id) {

        Account account = accountService.findById(id);
        return AccountMapper.toResponse(account);

    }

    @GetMapping
    public PageResponse<AccountResponse> findAll(Pageable pageable) {

        Page<Account> accounts = accountService.findAll(pageable);

        return new PageResponse<>(
                accounts.getContent()
                        .stream()
                        .map(AccountMapper::toResponse)
                        .toList(),
                accounts.getNumber(),
                accounts.getSize(),
                accounts.getTotalElements(),
                accounts.getTotalPages()
        );

    }

    @PostMapping("/{id}/deposit")
    public AccountResponse deposit(@PathVariable Long id, @Valid @RequestBody DepositRequest request) {
        Account account = accountService.deposit(id, request.amount());
        return AccountMapper.toResponse(account);
    };

    @PostMapping("/{id}/withdraw")
    public AccountResponse withdraw(@PathVariable Long id, @Valid @RequestBody WithdrawRequest request) {
        Account account = accountService.withdraw(id, request.amount());
        return AccountMapper.toResponse(account);
    }

}
