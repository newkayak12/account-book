package com.server.base.service;

import com.server.base.common.constants.Constants;
import com.server.base.common.dto.PagingContainer;
import com.server.base.common.dto.PagingDto;
import com.server.base.common.exception.Exceptions;
import com.server.base.common.exception.ServiceException;
import com.server.base.common.mapper.Mapper;
import com.server.base.repository.categoryRepository.Category;
import com.server.base.repository.categoryRepository.CategoryRepository;
import com.server.base.repository.categorySubRepository.CategorySub;
import com.server.base.repository.categorySubRepository.CategorySubRepository;
import com.server.base.repository.dto.CategoryDto;
import com.server.base.repository.dto.CategorySubDto;
import com.server.base.repository.dto.UserDto;
import com.server.base.repository.userRepository.User;
import com.server.base.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CategorySubRepository categorySubRepository;

    /**
     * 메인 카테고리 등록/ 수정
     * @param categoryDto
     */

    @Transactional(rollbackFor = Exception.class)
    public void saveMain(CategoryDto categoryDto) {
        User user = userRepository.getUserByUserNo(categoryDto.getUser().getUserNo());
        Category category = categoryRepository.findCategoryByCategoryNoAndUser(categoryDto.getCategoryNo(), user)
                .orElseGet(Category::new);
        category = Mapper.modelMapping(categoryDto, category);
        categoryRepository.save(category);
    }

    /**
     * 서브카테고리 등록/수정
     * @param categorySubDto
     */

    @Transactional(rollbackFor = Exception.class)
    public void saveSub(UserDto userDto, CategorySubDto categorySubDto) throws ServiceException {
        User user = userRepository.getUserByUserNo(userDto.getUserNo());
        log.warn("categorySubDto.getCategoryNo() {}, user {}", categorySubDto.getCategoryNo(), user);
        Category category = categoryRepository.findCategoryByCategoryNoAndUser(categorySubDto.getCategoryNo(), user)
                .orElseThrow(()->new ServiceException(Exceptions.EMPTY_DATA));
        CategorySub categorySub = categorySubRepository.getCategorySubByCategorySubNoAndUser(categorySubDto.getCategorySubNo(), user)
                        .orElseGet(CategorySub::new);

        if(categorySub.equals(new CategorySub())&&
                category.getCategory_etc2_list().size()>= Constants.SUB_CATEGORY_MAX_COUNT){
            throw new ServiceException(Exceptions.EXCEED_MAX_COUNT);
        }

        Mapper.modelMapping(categorySubDto, categorySub);
        categorySub.setMainCategory(category);
        categorySub.setUser(user);
        categorySubRepository.save(categorySub);
    }

    /**
     * 메인 카테고리 삭제
     * @param categoryDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void removeMain(CategoryDto categoryDto) throws ServiceException {
        User user = userRepository.getUserByUserNo(categoryDto.getUser().getUserNo());
        Category category = categoryRepository.findCategoryByCategoryNoAndUser(categoryDto.getCategoryNo(), user)
                .orElseThrow(()->new ServiceException(Exceptions.EMPTY_DATA));
        categoryRepository.delete(category);
    }

    /**
     * 서브 카테고리 삭제
     * @param categorySubDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void removeSub(CategorySubDto categorySubDto) throws ServiceException {
        User user = userRepository.getUserByUserNo(categorySubDto.getUser().getUserNo());
        CategorySub categorySub = categorySubRepository.getCategorySubByCategorySubNoAndUser(categorySubDto.getCategorySubNo(), user)
                .orElseThrow(()->new ServiceException(Exceptions.EMPTY_DATA));
        categorySubRepository.delete(categorySub);
    }

    /**
     * 메인 카테고리 가져오기
     * @param userDto
     * @param pagingDto
     * @return
     */
    public PagingContainer fetchMains(UserDto userDto, PagingDto pagingDto) {
        Pageable pageInfo = PageRequest.of(pagingDto.getPage(), pagingDto.getLimit(), Sort.by("categoryNo"));
        return new PagingContainer(pageInfo, categoryRepository.fetchMains(userDto, pageInfo));
    }


    public List<CategorySubDto> fetchSub(UserDto userDto, CategoryDto categoryDto) throws ServiceException {
        User user = userRepository.getUserByUserNo(userDto.getUserNo());
        Category category = categoryRepository.findCategoryByCategoryNoAndUser(categoryDto.getCategoryNo(), user)
                .orElseThrow(()->new ServiceException(Exceptions.EMPTY_DATA));
        return categorySubRepository.getCategorySubsByCategoryAndUser(category, user)
                .orElseThrow(()->new ServiceException(Exceptions.EMPTY_DATA)).stream().map(item->Mapper.modelMapping(item, new CategorySubDto()))
                .collect(Collectors.toList());
    }
}
