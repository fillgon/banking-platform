package com.banking.platform.accountservice.account.infrastruture.specification;

import com.banking.platform.accountservice.account.domain.Transaction;
import com.banking.platform.accountservice.account.domain.TransactionType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TransactionSpecification {

    private TransactionSpecification() {}

    public static Specification<Transaction> belongsToAccount(Long accountId) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.or(
                        criteriaBuilder.equal(
                          root.get("sourceAccountId"),
                          accountId
                        ),
                        criteriaBuilder.equal(
                                root.get("destinationAccountId"),
                                accountId
                        )
                ));
    }

    public static Specification<Transaction> hasType(TransactionType type) {

        return (root, query, criteriaBuilder) -> {

            if (type == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(
                    root.get("type"),
                    type
            );
        };
    }

    public static Specification<Transaction> createdBetween(LocalDateTime from, LocalDateTime to) {
        return (root, query, criteriaBuilder) -> {
            if (from == null || to == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.between(
                    root.get("createdAt"),
                    from,
                    to
            );
        };



    }

}
