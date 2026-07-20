package com.banking.platform.accountservice.account.application.service;

import com.banking.platform.accountservice.account.domain.Account;
import com.banking.platform.accountservice.account.domain.Transaction;
import com.banking.platform.accountservice.account.domain.TransactionType;
import com.banking.platform.accountservice.account.domain.exception.*;
import com.banking.platform.accountservice.account.infrastruture.repository.AccountRepository;
import com.banking.platform.accountservice.account.infrastruture.repository.TransactionRepository;
import com.banking.platform.accountservice.account.infrastruture.specification.TransactionSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
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
     BigDecimal balanceBefore = account.getBalance();

     account.setBalance(account.getBalance().add(amount));

     transactionRepository.save(
             Transaction.builder()
                     .type(TransactionType.DEPOSIT)
                     .sourceAccountId(account.getId())
                     .amount(amount)
                     .sourceBalanceBefore(balanceBefore)
                     .sourceBalanceAfter(account.getBalance())
                     .createdAt(LocalDateTime.now())
                     .build()
     );

        return account;
    }

    @Transactional
    public Account withdraw(Long id, BigDecimal amount) {
        Account account = findById(id);

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }

        BigDecimal balanceBefore = account.getBalance();

        account.setBalance(account.getBalance().subtract(amount));

        transactionRepository.save(Transaction.builder()
                        .type(TransactionType.WITHDRAW)
                        .sourceAccountId(account.getId())
                        .amount(amount)
                        .sourceBalanceBefore(balanceBefore)
                        .sourceBalanceAfter(account.getBalance())
                        .createdAt(LocalDateTime.now())
                        .build());

        return account;
    }

    @Transactional
    public Account transfer(Long sourceAccountId, Long destinationAccountId, BigDecimal amount) {
        if (sourceAccountId.equals(destinationAccountId)) {
            throw new SameAccountTransferException();
        }

        Account sourceAccount = findById(sourceAccountId);
        Account destinationAccount = findById(destinationAccountId);

        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }

        BigDecimal sourceBalanceBefore = sourceAccount.getBalance();
        BigDecimal destinationBalanceBefore = destinationAccount.getBalance();

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        destinationAccount.setBalance(destinationAccount.getBalance().add(amount));

        transactionRepository.save(Transaction.builder()
                        .type(TransactionType.TRANSFER)
                        .sourceAccountId(sourceAccount.getId())
                        .destinationAccountId(destinationAccount.getId())
                        .amount(amount)
                        .sourceBalanceBefore(sourceBalanceBefore)
                        .sourceBalanceAfter(sourceAccount.getBalance())
                        .destinationBalanceBefore(destinationBalanceBefore)
                        .destinationBalanceAfter(destinationAccount.getBalance())
                        .createdAt(LocalDateTime.now())
                .build());

        return sourceAccount;
    }

    public Page<Transaction> findStatement(
            Long accountId,
            TransactionType type,
            LocalDateTime from,
            LocalDateTime to,
            Pageable pageable
    ) {
        findById(accountId);

        boolean onlyFromProvided = from != null && to == null;
        boolean onlyToProvided = from == null && to != null;

        if (onlyFromProvided || onlyToProvided) {
            throw new InvalidStatementPeriodException();
        }

        Specification<Transaction> specification =
                Specification
                        .where(TransactionSpecification.belongsToAccount(accountId))
                        .and(TransactionSpecification.hasType(type)
                        .and(TransactionSpecification.createdBetween(from, to)));

        return transactionRepository.findAll(specification, pageable);

    }

}
