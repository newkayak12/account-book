package com.server.base.repository.accountCodeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountCodeRepository extends JpaRepository<AccountCode, Long> {
}