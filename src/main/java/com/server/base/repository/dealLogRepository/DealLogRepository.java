package com.server.base.repository.dealLogRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealLogRepository extends JpaRepository<DealLog, Long>, DealLogRepositoryCustom {

}
