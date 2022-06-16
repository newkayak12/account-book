package com.server.base.repository.mainAccount;

import com.server.base.common.dto.PagingDto;
import com.server.base.repository.dto.UserDto;

import java.util.List;

public interface MainAccountRepositoryCustom {
    List<MainAccount> fetchMainAccount(UserDto userDto, PagingDto pagingDto);
    List<MainAccount> fetchTotalInAndOut(UserDto userDto, PagingDto pagingDto);
}
