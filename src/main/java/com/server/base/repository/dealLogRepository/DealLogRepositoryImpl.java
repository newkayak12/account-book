package com.server.base.repository.dealLogRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.base.common.dto.PagingDto;
import com.server.base.common.mapper.Mapper;
import com.server.base.repository.dto.CategoryDto;
import com.server.base.repository.dto.DealLogDto;
import com.server.base.repository.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.server.base.repository.dealLogRepository.QDealLog.dealLog;
public class DealLogRepositoryImpl extends QuerydslRepositorySupport implements DealLogRepositoryCustom{
    private JPAQueryFactory query;

    @Autowired
    public DealLogRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(DealLog.class);
        this.query= jpaQueryFactory;
    }

    @Override
    public void setNoCategoryWhenBasicCategoryHidden(UserDto userDto, CategoryDto categoryDto) {
                update(dealLog).setNull(dealLog.category).where(dealLog.user.userNo.eq(userDto.getUserNo())
                .and(dealLog.category.categoryNo.eq(categoryDto.getCategoryNo()))).execute();
    }

    @Override
    public Optional<DealLog> findDealLogByDealLogNoAndUserNo(Long dealLogNo, Long userNo) {
        return Optional.ofNullable(query.select(dealLog).from(dealLog)
                .where(dealLog.dealLogNo.eq(dealLogNo).and(dealLog.user.userNo.eq(userNo)))
                .fetchOne());
    }

    @Override
    public void removeDealLog(Long dealLogNo, Long userNo) {
        delete(dealLog).where(dealLog.dealLogNo.eq(dealLogNo).and(dealLog.user.userNo.eq(userNo))).execute();
    }

    @Override
    public List<DealLogDto> fetchList(PagingDto pagingDto, Long userNo) {
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(!Objects.isNull(pagingDto.getIsOutcome())){
            conditionBuilder.and(dealLog.isOutcome.eq(pagingDto.getIsOutcome()));
        }
        if(!StringUtils.isEmpty(pagingDto.getSearchText())){
            conditionBuilder.and(dealLog.dealContent.contains(pagingDto.getSearchText()));
        }
        if(!Objects.isNull(pagingDto.getTableNo())){
            conditionBuilder.and(dealLog.category.categoryNo.eq(pagingDto.getTableNo()));
        }
        if(!Objects.isNull(pagingDto.getStartDate())){
            conditionBuilder.and(dealLog.dealDate.between(pagingDto.getStartDate().atStartOfDay().toLocalDate(),
                    pagingDto.getEndDate().plusDays(1).atStartOfDay().minusNanos(1L).toLocalDate()));
        }

        return query.select(dealLog).from(dealLog).where(dealLog.user.userNo.eq(userNo).and(conditionBuilder)).fetch()
                .stream().map(i-> Mapper.modelMapping(i, new DealLogDto())).collect(Collectors.toList());
    }
}
