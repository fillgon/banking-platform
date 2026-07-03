package com.banking.platform.accountservice.account.interfaces.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountResponse(
        Long id,
        String accountNumber,
        String ownerName,
        BigDecimal balance,
        LocalDateTime createdAt
) {}
