package com.banking.platform.accountservice.account.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private TransactionType type;

    @Column(nullable=false)
    private Long sourceAccountId;

    private Long destinationAccountId;

    @Column(nullable=false)
    private BigDecimal amount;

    @Column(nullable=false)
    private LocalDateTime createdAt;

    private BigDecimal sourceBalanceBefore;
    private BigDecimal sourceBalanceAfter;
    private BigDecimal destinationBalanceBefore;
    private BigDecimal destinationBalanceAfter;
}
