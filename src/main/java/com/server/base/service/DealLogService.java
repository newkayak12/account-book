package com.server.base.service;

import com.server.base.common.dto.PagingDto;
import com.server.base.common.enums.Type;
import com.server.base.common.exception.Exceptions;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.mapper.Mapper;
import com.server.base.repository.categoryRepository.Category;
import com.server.base.repository.dealLogRepository.DealLog;
import com.server.base.repository.dealLogRepository.DealLogRepository;
import com.server.base.repository.dto.CategoryDto;
import com.server.base.repository.dto.DealLogDto;
import com.server.base.repository.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class DealLogService {
    private final DealLogRepository dealLogRepository;

    /**
     * 기본 카테고리 미분류 처리 (숨김처리시)
     * @param userDto
     * @param categoryDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void setNoCategory(UserDto userDto, CategoryDto categoryDto){
       dealLogRepository.setNoCategoryWhenBasicCategoryHidden(userDto, categoryDto);
    }

    /**
     * 가계부 저장/수정
     * @param dealLogDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveDealLog(DealLogDto dealLogDto) {
        DealLog dealLog = dealLogRepository.findDealLogByDealLogNoAndUserNo(dealLogDto.getDealLogNo(), dealLogDto.getUser().getUserNo())
                .orElseGet(DealLog::new);

        if(Objects.isNull(dealLog.getDealLogNo())){
            dealLog.changeCategory(Mapper.modelMapping(dealLogDto.getCategory(), new Category()));
            dealLog.changeMemo(dealLog.getDealContent());
            dealLog.changePlusMinus(dealLog.getIsOutcome());
            dealLog.changePrice(dealLog.getDealPrice());
        }
        if(!Objects.isNull(dealLog.getDealLogNo())){
            Mapper.modelMapping(dealLogDto, dealLog);
            dealLogRepository.save(dealLog);
        }
    }

    /**
     * 가계부 삭제
     * @param dealLogDto
     */
    public void removeDealLog(DealLogDto dealLogDto) {
        dealLogRepository.removeDealLog(dealLogDto.getDealLogNo(), dealLogDto.getUser().getUserNo());
    }

    /**
     * 단건
     * @param dealLogDto
     * @return
     * @throws ServiceException
     */
    public DealLogDto fetchDealLog(DealLogDto dealLogDto) throws ServiceException {
        return Mapper.modelMapping(dealLogRepository.findDealLogByDealLogNoAndUserNo(dealLogDto.getDealLogNo(), dealLogDto.getUser().getUserNo())
                .orElseThrow(()->new ServiceException(Exceptions.EMPTY_DATA)), new DealLogDto());
    }

    /**
     * 여러 건
     * @param userDto
     * @param pagingDto
     * @return
     */
    public Map fetchDealLogList(UserDto userDto, PagingDto pagingDto) throws ServiceException {
        if(Objects.isNull(pagingDto.getType())){
            throw new ServiceException(Exceptions.NO_TYPE_HAS_BEEN_BOUND);
        }
//날짜 설정
        if(Type.MONTH.equals(pagingDto.getType())){

        }
        if(Type.WEEK.equals(pagingDto.getType())){

        }
        if(Type.DAY.equals(pagingDto.getType())){

        }

        List<DealLogDto> list = dealLogRepository.fetchList(pagingDto, userDto.getUserNo());
        Map result = new HashMap();

        result.put("totalIncome", list.stream().filter(i->!i.getIsOutcome()).map(DealLogDto::getDealPrice).mapToLong(i->i).sum());
        result.put("totalOutcome", list.stream().filter(i->i.getIsOutcome()).map(DealLogDto::getDealPrice).mapToLong(i->-i).sum());

        return  null;
    }
}
