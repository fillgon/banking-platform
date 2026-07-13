package com.banking.platform.accountservice.account.interfaces.dto;

import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;

public record DepositRequest(

        @DecimalMin(value = "0.01", message = "O valor do depósito deve ser maior que zero.")
        BigDecimal amount
) {
}
