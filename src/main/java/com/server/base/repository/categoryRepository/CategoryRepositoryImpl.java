package com.server.base.repository.categoryRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.base.common.mapper.Mapper;
import com.server.base.repository.dto.CategoryDto;
import com.server.base.repository.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

import static com.server.base.repository.categoryRepository.QCategory.category;

public class CategoryRepositoryImpl extends QuerydslRepositorySupport implements CategoryRepositoryCustom {
    private JPAQueryFactory query;

    @Autowired
    public CategoryRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Category.class);
        this.query = jpaQueryFactory;
    }

    @Override
    public List<CategoryDto> fetchCategories(UserDto userDto) {
        return query.select(category).from(category).where(category.user.userNo.eq(userDto.getUserNo())).fetch()
                .stream().map(i->Mapper.modelMapping(i, new CategoryDto())).collect(Collectors.toList());
    }
}