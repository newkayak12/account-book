package com.server.base.repository.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    User getUserByUserNo(Long userNo);
    Optional<User> getUserByUserId(String userId);
    Optional<User> getUserByUserIdAndUserLockDateBefore(String userId, LocalDateTime now);
}
