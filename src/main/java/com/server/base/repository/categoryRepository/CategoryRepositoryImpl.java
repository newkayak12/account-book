package com.server.base.repository.categoryRepository;

import com.querydsl.core.QueryResults;
import com.server.base.repository.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.server.base.repository.categoryRepository.QCategory.category;

public class CategoryRepositoryImpl extends QuerydslRepositorySupport implements CategoryRepositoryCustom{
    public CategoryRepositoryImpl() {
        super(Category.class);
    }

    @Override
    @EntityGraph(attributePaths = {"category_etc2_list"}, type = EntityGraph.EntityGraphType.FETCH)
    public Page<Category> fetchMains(UserDto userDto, Pageable pageable) {
        QueryResults<Category> queryResults = from(category)
//                .fetchJoin()
//                .leftJoin(category.category_etc2_list, categorySub)
                .where(category.user.userNo.eq(userDto.getUserNo())
                        .or(category.user.userNo.eq(-1L)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    @Override
    public List<Category> fetchMainsNotPaging(UserDto userDto) {
        return from(category).where(category.user.userNo.isNull().and(category.user.userNo.eq(userDto.getUserNo()))).fetch();
    }
}
