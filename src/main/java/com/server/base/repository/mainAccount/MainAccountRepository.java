package com.server.base.repository.mainAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MainAccountRepository extends JpaRepository<MainAccount, Long>, MainAccountRepositoryCustom {
    Optional<MainAccount> getMainAccountByMainAccountNo(Long accountNo);
}