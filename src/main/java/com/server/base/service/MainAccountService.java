package com.server.base.service;

import com.server.base.common.dto.PagingDto;
import com.server.base.common.enums.Type;
import com.server.base.common.exception.Exceptions;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.mapper.Mapper;
import com.server.base.repository.dto.MainAccountDto;
import com.server.base.repository.dto.UserDto;
import com.server.base.repository.mainAccount.MainAccount;
import com.server.base.repository.mainAccount.MainAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainAccountService {
    private final MainAccountRepository mainAccountRepository;

    /**
     * 등록/수정
     * @param mainAccountDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveAccount(MainAccountDto mainAccountDto) {
        MainAccount mainAccount = mainAccountRepository.getMainAccountByMainAccountNo(mainAccountDto.getMainAccountNo()).orElseGet(MainAccount::new);
        Mapper.modelMapping(mainAccountDto, mainAccount);
        mainAccountRepository.save(mainAccount);
    }

    /**
     * 삭제
     * @param mainAccountDto
     * @throws ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    public void removeAccount(MainAccountDto mainAccountDto) throws ServiceException {
        MainAccount mainAccount = mainAccountRepository.getMainAccountByMainAccountNo(mainAccountDto.getMainAccountNo()).orElseThrow(()->new ServiceException(Exceptions.EMPTY_DATA));
        mainAccountRepository.delete(mainAccount);
    }

    public Map<String,Object> fetchAccount(UserDto userDto, PagingDto pagingDto) {
        List<MainAccount> mainAccountDtoList = mainAccountRepository.fetchMainAccount(userDto, pagingDto);
        Map<String,Object> result = new HashMap<>();

            if(Type.MONTH.equals(pagingDto.getType())){
                LocalDate start = pagingDto.getStartDate().withDayOfMonth(1);
                LocalDate end = pagingDto.getEndDate().withDayOfMonth(pagingDto.getEndDate().lengthOfMonth());
                start.datesUntil(end).forEach(item->{
                    Integer day = item.getDayOfMonth();
                    result.put(day+" 일", mainAccountDtoList.stream().filter(value->value.getMainAccountDate().getDayOfMonth()==day).collect(Collectors.toList()));
                });
            }
            if(Type.WEEK.equals(pagingDto.getType())){
                LocalDate start = pagingDto.getStartDate().withDayOfMonth(1);
                LocalDate end = pagingDto.getEndDate().withDayOfMonth(pagingDto.getEndDate().lengthOfMonth());
                Object[][] date = new Object[5][7];
                start.datesUntil(end).forEach(item->{
                     Integer day = item.getDayOfMonth();
                     Double temp = Math.ceil(item.getDayOfMonth()/7);
                     DayOfWeek dayOfWeek = item.getDayOfWeek();
                     Integer week = temp.intValue();
                     Integer dayOfWeekValue = dayOfWeek.getValue();
                    date[week][dayOfWeekValue]=mainAccountDtoList.stream().filter(value->value.getMainAccountDate().getDayOfMonth()==day).collect(Collectors.toList());
                    if(dayOfWeekValue==7){
                        result.put(week+" 주", date[week]);
                    }
                });
            }

            if(Type.DAY.equals(pagingDto.getType())){
                Integer day = LocalDate.now().getDayOfMonth();
                result.put(day+" 일", mainAccountDtoList.stream().filter(value->value.getMainAccountDate().getDayOfMonth()==day).collect(Collectors.toList()));
            }
        return result;
    }
}
