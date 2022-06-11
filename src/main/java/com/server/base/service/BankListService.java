package com.server.base.service;

import com.server.base.common.exception.Exceptions;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.mapper.Mapper;
import com.server.base.repository.bankListRepository.BankList;
import com.server.base.repository.bankListRepository.BankListRepository;
import com.server.base.repository.dto.BankListDto;
import com.server.base.repository.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BankListService {
    private final BankListRepository bankListRepository;

    /**
     * 사용자 은행 리스트 등록/수정
     * @param bankListDto
     */
    public void saveBankList(BankListDto bankListDto) {
        BankList bankList = Mapper.modelMapping(bankListDto, new BankList());
        bankListRepository.save(bankList);
    }

    /**
     * 사용자 은행 리스트 삭제
     * @param bankListDto
     * @throws ServiceException
     */
    public void deleteBankList(BankListDto bankListDto) throws ServiceException {
       BankList bankList =  bankListRepository.getBankListByBankListNoAndUserNo(bankListDto.getBankListNo(), bankListDto.getUserNo())
                .orElseThrow(()-> new ServiceException(Exceptions.EMPTY_DATA));
       bankListRepository.delete(bankList);
    }

    /**
     * 사용자 은행 리스트 가져오기
     * @param authorizations
     * @return
     */
    public List<BankList> fetchUserBankList(UserDto authorizations) {
        return bankListRepository.getBankListsByUserNo(authorizations.getUserNo());
    }
}
