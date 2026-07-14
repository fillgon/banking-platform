package com.banking.platform.accountservice.account.infrastruture.repository;

import com.banking.platform.accountservice.account.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
