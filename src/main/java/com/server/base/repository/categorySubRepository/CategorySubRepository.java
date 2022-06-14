package com.server.base.repository.categorySubRepository;

import com.server.base.repository.categoryRepository.Category;
import com.server.base.repository.userRepository.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategorySubRepository extends JpaRepository<CategorySub, Long> {
    Optional<CategorySub> getCategorySubByCategorySubNoAndUser(Long categorySubNo, User user);
    Optional<List<CategorySub>> getCategorySubsByCategoryAndUser(Category category, User user);
}