package com.banking.platform.accountservice.account.interfaces.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateAccountRequest(

        @NotBlank
        String ownerName

) {
}