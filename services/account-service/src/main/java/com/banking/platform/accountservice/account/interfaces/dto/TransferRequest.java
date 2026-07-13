package com.banking.platform.accountservice.account.interfaces.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferRequest(
        @NotNull(message = "A conta de destino é obrigatória.") Long destinationAccountId,
        @NotNull(message = "O Valor da transferência é obrigatório.")
        @DecimalMin(value = "0.01", message = "O valor da transferência deve ser maior que zero.") BigDecimal amount
        ) {
}
