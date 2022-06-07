package com.server.base.service;

import com.server.base.common.mapper.Mapper;
import com.server.base.repository.bankCodeRepository.BankCodeRepository;
import com.server.base.repository.dto.BankCodeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BankCodeService {
    private final BankCodeRepository bankCodeRepository;

    public List<BankCodeDto> fetchBankList() {
        return  bankCodeRepository.findAll().stream().map(item-> Mapper.modelMapping(item, new BankCodeDto()))
                .collect(Collectors.toList());

    }
}
