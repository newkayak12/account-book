package com.server.base.repository.bankListRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankListRepository extends JpaRepository<BankList, Long> {
    public Optional<BankList> getBankListByBankListNoAndUserNo(Long bankListNo, Long userNo);
    public List<BankList> getBankListsByUserNo(Long userNo);
}