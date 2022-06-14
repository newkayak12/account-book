package com.server.base.service;

import com.server.base.common.dto.PagingDto;
import com.server.base.common.exception.Exceptions;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.mapper.Mapper;
import com.server.base.repository.categoryRepository.CategoryRepository;
import com.server.base.repository.dto.MyMoneyDto;
import com.server.base.repository.dto.UserDto;
import com.server.base.repository.myMoneyRepository.MyMoney;
import com.server.base.repository.myMoneyRepository.MyMoneyRepository;
import com.server.base.repository.userRepository.User;
import com.server.base.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyMoneyService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final MyMoneyRepository myMoneyRepository;

    /**
     * 예산 등록/ 수정
     * @param myMoneyDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveMyMoney(MyMoneyDto myMoneyDto) {
        User user = userRepository.getUserByUserNo(myMoneyDto.getUser().getUserNo());
        MyMoney myMoney = myMoneyRepository.getMyMoneyByMyMoneyNoAndUser(myMoneyDto.getMyMoneyNo(), user)
                .orElseGet(MyMoney::new);
        Mapper.modelMapping(myMoneyDto, myMoney);
        myMoney.setUser(user);
        myMoneyRepository.save(myMoney);
    }

    /**
     * 예산 삭제
     * @param myMoneyDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void removeMyMoney(MyMoneyDto myMoneyDto) throws ServiceException {
        User user = userRepository.getUserByUserNo(myMoneyDto.getUser().getUserNo());
        MyMoney myMoney = myMoneyRepository.getMyMoneyByMyMoneyNoAndUser(myMoneyDto.getMyMoneyNo(),user)
                .orElseThrow(()->new ServiceException(Exceptions.EMPTY_DATA));
        myMoneyRepository.delete(myMoney);
    }

    /**
     * 예산 가져오기
     * @param userDto
     * @param pagingDto
     * @return
     */
    @Transactional(readOnly = true)
    public MyMoneyDto fetchMyMoney(UserDto userDto, PagingDto pagingDto) {
        User user = userRepository.getUserByUserNo(userDto.getUserNo());
        List<MyMoney> myMoney = myMoneyRepository.getMyMoneyByUserAndDateBetween(user, pagingDto);
        return Mapper.modelMapping(myMoney, new MyMoneyDto());
    }
}
