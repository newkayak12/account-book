package com.server.base.repository.myMoneyRepository;

import com.server.base.common.dto.PagingDto;
import com.server.base.repository.categoryRepository.QCategory;
import com.server.base.repository.userRepository.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.server.base.repository.myMoneyRepository.QMyMoney.myMoney;
public class MyMoneyRepositoryImpl extends QuerydslRepositorySupport implements MyMoneyRepositoryCustom {
    public MyMoneyRepositoryImpl() {
        super(QMyMoney.class);
    }

    @Override
    public List<MyMoney> getMyMoneyByUserAndDateBetween(User user, PagingDto pagingDto) {
        return from(myMoney).fetchJoin().leftJoin(QCategory.category)
                .on(myMoney.categoryNo.eq(QCategory.category.categoryNo))
                .where(myMoney.date.between(pagingDto.getStartDate().withDayOfMonth(1).atStartOfDay().toLocalDate(),
                        pagingDto.getEndDate().withDayOfMonth(pagingDto.getStartDate().lengthOfMonth()).atStartOfDay().plusDays(1).minusSeconds(1).toLocalDate()))
                .orderBy(myMoney.categoryNo.desc(), myMoney.date.asc())
                .fetch();
    }
}
