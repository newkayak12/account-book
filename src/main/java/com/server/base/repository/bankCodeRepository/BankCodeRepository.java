package com.server.base.repository.bankCodeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCodeRepository extends JpaRepository<BankCode, Long> {
}