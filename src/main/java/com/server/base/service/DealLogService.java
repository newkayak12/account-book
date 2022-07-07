package com.server.base.service;

import com.server.base.common.dto.PagingDto;
import com.server.base.common.enums.Type;
import com.server.base.common.exception.Exceptions;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.mapper.Mapper;
import com.server.base.repository.categoryRepository.Category;
import com.server.base.repository.categoryRepository.CategoryRepository;
import com.server.base.repository.dealLogRepository.DealLog;
import com.server.base.repository.dealLogRepository.DealLogRepository;
import com.server.base.repository.dto.CategoryDto;
import com.server.base.repository.dto.DealLogDto;
import com.server.base.repository.dto.UserDto;
import com.server.base.repository.userRepository.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DealLogService {
    private final DealLogRepository dealLogRepository;
    private final CategoryRepository categoryRepository;

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
        DealLog dealLog = null;
        if(Objects.isNull(dealLogDto.getDealLogNo())){
            dealLog = new DealLog();
        }
        if(!Objects.isNull(dealLogDto.getDealLogNo())){
            dealLog = dealLogRepository.findDealLogByDealLogNoAndUserNo(dealLogDto.getDealLogNo(), dealLogDto.getUser().getUserNo())
                    .orElseGet(DealLog::new);
        }

        User user = Mapper.modelMapping(dealLogDto.getUser(), new User());
        Category category = Mapper.modelMapping(dealLogDto.getCategory(), new Category());
        if(Objects.isNull(dealLog.getDealLogNo())){
            dealLog.changeCategory(category);
            dealLog.setUser(user);
            dealLog.changeMemo(dealLogDto.getDealContent());
            dealLog.changePlusMinus(dealLogDto.getIsOutcome());
            dealLog.changePrice(dealLogDto.getDealPrice());
            dealLogRepository.save(dealLog);
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
    @Transactional
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
        List<DealLogDto> list = dealLogRepository.fetchList(pagingDto, userDto.getUserNo());
        Map result = new HashMap();

        result.put("totalIncome", list.stream().filter(i->!i.getIsOutcome()).map(DealLogDto::getDealPrice).mapToLong(i->i).sum());
        result.put("totalOutcome", list.stream().filter(i->i.getIsOutcome()).map(DealLogDto::getDealPrice).mapToLong(i->-i).sum());

        LocalDate monthStart = pagingDto.getStartDate().withDayOfMonth(1);;
        LocalDate monthEnd = pagingDto.getEndDate().withDayOfMonth(pagingDto.getEndDate().lengthOfMonth());;
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(Date.from(monthStart.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(Date.from(monthEnd.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        Integer weekCount = endCalendar.get(Calendar.WEEK_OF_MONTH);
//        if(startCalendar.DAY_OF_WEEK)

        Object[][] cal = new Object[weekCount][7];

        if(Type.MONTH.equals(pagingDto.getType())){
            Map calz = new HashMap();
            monthStart.datesUntil(monthEnd.plusDays(1)).forEach(item->{
                Calendar calendar = Calendar.getInstance();
                calendar.set(item.getYear(), item.getMonth().getValue(), item.getDayOfMonth());
                Integer day = calendar.get(Calendar.DAY_OF_WEEK);
                Integer week = calendar.get(Calendar.WEEK_OF_MONTH);

                List<DealLogDto> dayList = list.stream().filter(val-> {
                    Calendar c = Calendar.getInstance();
                    c.set(val.getDealDate().getYear(), val.getDealDate().getMonth().getValue(), val.getDealDate().getDayOfMonth());

                    return (week.equals(c.get(Calendar.WEEK_OF_MONTH)) &&day.equals(c.get(Calendar.DAY_OF_WEEK)));
                }).collect(Collectors.toList());
                calz.put(day, Map.of("income", dayList.stream().filter(value-> !value.getIsOutcome()).collect(Collectors.toList()), "outcome", dayList.stream().filter(value-> value.getIsOutcome()).collect(Collectors.toList())));
            });
            result.put("calendar", calz);
        }
        if(Type.WEEK.equals(pagingDto.getType())){
            Calendar calendarTemp = Calendar.getInstance();
            calendarTemp.set(monthEnd.getYear(), monthEnd.getMonth().getValue(), monthEnd.getDayOfMonth());
            monthStart.datesUntil(monthEnd).forEach(item->{
                Calendar calendar = Calendar.getInstance();
                calendar.set(item.getYear(), item.getMonth().getValue(), item.getDayOfMonth());


                Integer day = calendar.get(Calendar.DAY_OF_WEEK);
                Integer week = calendar.get(Calendar.WEEK_OF_MONTH);

                List<DealLogDto> weekList = list.stream().filter(value->{
                    Calendar c = Calendar.getInstance();
                    c.set(value.getDealDate().getYear(), value.getDealDate().getMonth().getValue(), value.getDealDate().getDayOfMonth());
                    return (week.equals(c.get(Calendar.WEEK_OF_MONTH)));
                }).collect(Collectors.toList());


                cal[week-1][day-1] = Map.of("income", weekList.stream().filter(v->!v.getIsOutcome()).collect(Collectors.toList()), "outcome", weekList.stream().filter(v->v.getIsOutcome()).collect(Collectors.toList()));
            });
            result.put("calendar", cal);
        }
        if(Type.DAY.equals(pagingDto.getType())){
            Map calz = new HashMap();
            monthStart.datesUntil(monthEnd.plusDays(1)).forEach(item->{
                Integer day = item.getDayOfMonth();
                List<DealLogDto> dayList = list.stream().filter(value->value.getDealDate().getDayOfMonth()==day).collect(Collectors.toList());
                calz.put(day, Map.of("income", dayList.stream().filter(value-> value.getIsOutcome()).collect(Collectors.toList()), "outcome", dayList.stream().filter(value-> !value.getIsOutcome()).collect(Collectors.toList())));
            });
            result.put("calendar",calz);
        }
        return  result;
    }

    public Map fetchStatistics(UserDto userDto, PagingDto pagingDto) {
        Map result = new HashMap();
        List<CategoryDto> categoryDtoList = categoryRepository.fetchCategories(userDto);
        List<DealLogDto> list = dealLogRepository.fetchList(pagingDto, userDto.getUserNo());
        List<CategoryDto> incomeCategories = categoryDtoList.stream().filter(i->!i.getIsOutcome()).collect(Collectors.toList());
        List<CategoryDto> outcomeCategories = categoryDtoList.stream().filter(i->i.getIsOutcome()).collect(Collectors.toList());
        Map outMap = new HashMap();
        incomeCategories.stream().forEach(i->{
            outMap.put(i.getCateName(), list.stream().filter(j->j.getCategory().getCateName().equals(i.getCateName())&&j.getIsOutcome().equals(i.getIsOutcome())).collect(Collectors.toList()));
        });
        result.put("outcome", outMap);
        Map inMap = new HashMap();
        outcomeCategories.stream().forEach(i->{
            inMap.put(i.getCateName(), list.stream().filter(j->j.getCategory().getCateName().equals(i.getCateName())&&j.getIsOutcome().equals(i.getIsOutcome())).collect(Collectors.toList()));
        });
        result.put("income", inMap);
        return result;
    }
}
