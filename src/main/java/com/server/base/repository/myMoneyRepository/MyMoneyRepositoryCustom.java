package com.server.base.repository.myMoneyRepository;

import com.server.base.common.dto.PagingDto;
import com.server.base.repository.userRepository.User;

import java.util.List;

public interface MyMoneyRepositoryCustom {
    List<MyMoney> getMyMoneyByUserAndDateBetween(User user, PagingDto pagingDto);
}
