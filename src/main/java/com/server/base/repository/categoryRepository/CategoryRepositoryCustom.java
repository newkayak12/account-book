package com.server.base.repository.categoryRepository;

import com.server.base.repository.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CategoryRepositoryCustom {
    Page<Category> fetchMains (UserDto userDto, Pageable pageable);
    List<Category> fetchMainsNotPaging(UserDto userDto);
}
