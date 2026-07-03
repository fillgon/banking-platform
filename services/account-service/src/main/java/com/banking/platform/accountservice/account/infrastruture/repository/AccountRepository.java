package com.banking.platform.accountservice.account.infrastruture.repository;

import com.banking.platform.accountservice.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
