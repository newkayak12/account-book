package com.server.base.repository.myMoneyRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyMoneyRepository extends JpaRepository<MyMoney, Long> {
}