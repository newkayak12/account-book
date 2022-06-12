package com.server.base.service;

import com.server.base.common.mapper.Mapper;
import com.server.base.repository.categoryRepository.Category;
import com.server.base.repository.categoryRepository.CategoryRepository;
import com.server.base.repository.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    /**
     * 메인 카테고리 등록/ 수정
     * @param categoryDto
     */

    @Transactional(rollbackFor = Exception.class)
    public void saveMain(CategoryDto categoryDto) {
        Category category = Mapper.modelMapping(categoryDto, new Category());
        categoryRepository.save(category);
    }
}
