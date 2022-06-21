package com.server.base.service;

import com.server.base.repository.dealLogRepository.DealLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DealLogService {
    private final DealLogRepository dealLogRepository;
}
