package com.server.base.repository.bankListRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankListRepository extends JpaRepository<BankList, Long> {
}