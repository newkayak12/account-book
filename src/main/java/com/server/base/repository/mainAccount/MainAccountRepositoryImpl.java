package com.server.base.repository.mainAccount;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.base.common.dto.PagingDto;
import com.server.base.repository.categoryRepository.QCategory;
import com.server.base.repository.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Objects;

import static com.server.base.repository.mainAccount.QMainAccount.mainAccount;

public class MainAccountRepositoryImpl extends QuerydslRepositorySupport implements MainAccountRepositoryCustom {
    @Autowired
    private JPAQueryFactory query;
    public MainAccountRepositoryImpl() {
        super(MainAccount.class);
    }



    @Override
    public List<MainAccount> fetchMainAccount(UserDto userDto, PagingDto pagingDto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(!Objects.isNull(pagingDto.getIsIncome())){
            if(pagingDto.getIsIncome()){
                booleanBuilder.and(mainAccount.mainAccountPrice.notLike("-%"));
            } else {
                booleanBuilder.and(mainAccount.mainAccountPrice.contains("-"));
            }
        }

        return from(mainAccount).
                fetchJoin()
                .join(QCategory.category).on(mainAccount.category.categoryNo.eq(QCategory.category.categoryNo))
                .where(mainAccount.userNo.eq(userDto.getUserNo())
                        .and(mainAccount.mainAccountDate.between(pagingDto.getStartDate().withDayOfMonth(1).atStartOfDay(),
                                pagingDto.getEndDate().withDayOfMonth(pagingDto.getEndDate().lengthOfMonth()).atStartOfDay().plusDays(1).minusSeconds(1)))
                        .and(booleanBuilder)
                )
                .fetch();
    }

    @Override
    public List<MainAccount> fetchTotalInAndOut(UserDto userDto, PagingDto pagingDto) {

        return from(mainAccount).where(mainAccount.userNo.eq(userDto.getUserNo()).and(mainAccount.mainAccountDate.between(pagingDto.getStartDate().withDayOfMonth(1).atStartOfDay(),
                pagingDto.getEndDate().withDayOfMonth(pagingDto.getEndDate().lengthOfMonth()).atStartOfDay().plusDays(1).minusSeconds(1))))
                .fetch();
    }
}
