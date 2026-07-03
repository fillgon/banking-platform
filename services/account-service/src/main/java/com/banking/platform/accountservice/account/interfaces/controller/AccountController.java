package com.banking.platform.accountservice.account.interfaces.controller;

import com.banking.platform.accountservice.account.application.service.AccountService;
import com.banking.platform.accountservice.account.domain.Account;
import com.banking.platform.accountservice.account.interfaces.dto.AccountResponse;
import com.banking.platform.accountservice.account.interfaces.dto.CreateAccountRequest;
import com.banking.platform.accountservice.account.interfaces.mapper.AccountMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
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

}
