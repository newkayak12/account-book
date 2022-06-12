package com.server.base.repository.categoryRepository;

import com.server.base.repository.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CategoryRepositoryCustom {
    Page<Category> fetchMains (UserDto userDto, Pageable pageable);
}
