package com.server.base.service;

import com.server.base.common.exception.Exceptions;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.mapper.Mapper;
import com.server.base.repository.bankListRepository.BankList;
import com.server.base.repository.bankListRepository.BankListRepository;
import com.server.base.repository.dto.BankListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BankListService {
    private final BankListRepository bankListRepository;

    public void saveBankList(BankListDto bankListDto) {
        BankList bankList = Mapper.modelMapping(bankListDto, new BankList());
        bankListRepository.save(bankList);
    }

    public void deleteBankList(BankListDto bankListDto) throws ServiceException {
       BankList bankList =  bankListRepository.getBankListByBankListNoAndUserNo(bankListDto.getBankListNo(), bankListDto.getUserNo())
                .orElseThrow(()-> new ServiceException(Exceptions.EMPTY_DATA));
       bankListRepository.delete(bankList);
    }
}
