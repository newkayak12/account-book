package com.server.base.repository.myMoneyRepository;

import com.server.base.repository.userRepository.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyMoneyRepository extends JpaRepository<MyMoney, Long>, MyMoneyRepositoryCustom {
    Optional<MyMoney> getMyMoneyByMyMoneyNoAndUser(Long myMoneyNo, User user);

}