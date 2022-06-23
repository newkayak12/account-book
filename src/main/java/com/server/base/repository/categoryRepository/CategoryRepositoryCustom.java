package com.server.base.repository.categoryRepository;

import com.server.base.repository.dto.CategoryDto;
import com.server.base.repository.dto.UserDto;

import java.util.List;

public interface CategoryRepositoryCustom {
    List<CategoryDto> fetchCategories(UserDto userDto);
}