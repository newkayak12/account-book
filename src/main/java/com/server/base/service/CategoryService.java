package com.server.base.service;

import com.server.base.common.exception.Exceptions;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.mapper.Mapper;
import com.server.base.repository.categoryRepository.Category;
import com.server.base.repository.categoryRepository.CategoryRepository;
import com.server.base.repository.dto.CategoryDto;
import com.server.base.repository.dto.UserDto;
import com.server.base.repository.userRepository.User;
import com.server.base.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    /**
     * 카테고리 가져오기
     * @param userDto
     * @return
     */
    @Transactional(readOnly = true)
    public List<CategoryDto> fetchCategories(UserDto userDto) {
        return categoryRepository.fetchCategories(userDto);
    }

    /**
     * 카테고리 등록/수정
     * @param categoryDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.findCategoryByCategoryNo(categoryDto.getCategoryNo())
                .orElseGet(Category::new);

        if(Objects.isNull(category.getCategoryNo())){
            User user = userRepository.getUserByUserNo(categoryDto.getUser().getUserNo());
            category= Mapper.modelMapping(categoryDto, category);
            category.setUser(user);
            categoryRepository.save(category);
        }
        if(!Objects.isNull(category.getCategoryNo())){
            category.changeCategoryName(categoryDto.getCateName());
            category.changeImage(category.getCateImage());
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void removeCategory(CategoryDto categoryDto) throws ServiceException {
        Category category = categoryRepository.findCategoryByCategoryNo(categoryDto.getCategoryNo())
                .orElseThrow(()-> new ServiceException(Exceptions.EMPTY_DATA));

        if(category.getCateIsBasic()){
            category.hideCategory();
        }
        if(!category.getCateIsBasic()){
            categoryRepository.deleteByCategoryNo(categoryDto.getCategoryNo());
        }
    }
}
