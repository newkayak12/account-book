package com.server.base.repository.depositAccountRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositAccountRepository extends JpaRepository<DepositAccount, Long> {
}