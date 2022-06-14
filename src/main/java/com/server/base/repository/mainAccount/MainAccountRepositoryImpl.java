package com.server.base.repository.mainAccount;

import com.server.base.common.dto.PagingDto;
import com.server.base.repository.categoryRepository.QCategory;
import com.server.base.repository.dto.UserDto;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.server.base.repository.mainAccount.QMainAccount.mainAccount;

public class MainAccountRepositoryImpl extends QuerydslRepositorySupport implements MainAccountRepositoryCustom {
    public MainAccountRepositoryImpl() {
        super(QMainAccount.class);
    }

    @Override
    public List<MainAccount> fetchMainAccount(UserDto userDto, PagingDto pagingDto) {

        return from(mainAccount).
                fetchJoin()
                .join(QCategory.category).on(mainAccount.category.categoryNo.eq(QCategory.category.categoryNo))
                .where(mainAccount.userNo.eq(userDto.getUserNo())
                        .and(mainAccount.mainAccountDate.between(pagingDto.getStartDate().withDayOfMonth(1).atStartOfDay(),
                                pagingDto.getEndDate().withDayOfMonth(pagingDto.getEndDate().lengthOfMonth()).atStartOfDay().plusDays(1).minusSeconds(1))))
                .fetch();
    }
}
