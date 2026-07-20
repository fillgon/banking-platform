package com.banking.platform.accountservice.account.interfaces.dto;

import com.banking.platform.accountservice.account.domain.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        Long id,
        TransactionType type,
        Long sourceAccountId,
        Long destinationAccountId,
        BigDecimal amount,
        BigDecimal sourceBalanceBefore,
        BigDecimal sourceBalanceAfter,
        BigDecimal destinationBalanceBefore,
        BigDecimal destinationBalanceAfter,
        LocalDateTime createdAt
) {
}
