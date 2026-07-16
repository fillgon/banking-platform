package com.banking.platform.accountservice.account.interfaces.mapper;

import com.banking.platform.accountservice.account.domain.Transaction;
import com.banking.platform.accountservice.account.interfaces.dto.TransactionResponse;

public class TransactionMapper {

    private TransactionMapper() {}

    public static TransactionResponse toResponse(Transaction transaction) {

        return new TransactionResponse(
                transaction.getId(),
                transaction.getType(),
                transaction.getSourceAccountId(),
                transaction.getDestinationAccountId(),
                transaction.getAmount(),
                transaction.getCreatedAt()
        );

    }

}
