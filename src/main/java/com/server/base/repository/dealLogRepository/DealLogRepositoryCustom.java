package com.server.base.repository.dealLogRepository;

import com.server.base.common.dto.PagingDto;
import com.server.base.repository.dto.CategoryDto;
import com.server.base.repository.dto.DealLogDto;
import com.server.base.repository.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface DealLogRepositoryCustom {
    void setNoCategoryWhenBasicCategoryHidden(UserDto userDto, CategoryDto categoryDto);

    Optional<DealLog> findDealLogByDealLogNoAndUserNo(Long dealLogNo, Long userNo);
    void removeDealLog(Long dealLogNo, Long userNo);
    List<DealLogDto> fetchList(PagingDto pagingDto, Long userNo);
}
