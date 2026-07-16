package com.banking.platform.accountservice.account.infrastruture.repository;

import com.banking.platform.accountservice.account.domain.Transaction;
import com.banking.platform.accountservice.account.domain.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findBySourceAccountIdOrDestinationAccountId(
            Long sourceAccountId,
            Long destinationAccountId,
            Pageable pageable
    );

    @Query("""
        SELECT transaction
        FROM Transaction transaction
        WHERE (
            transaction.sourceAccountId = :accountId
            OR transaction.destinationAccountId = :accountId
        )
        AND transaction.type = :type
        """)
    Page<Transaction> findStatementByAccountIdAndType(
            @Param("accountId") Long accountId,
            @Param("type") TransactionType type,
            Pageable pageable
    );

}
