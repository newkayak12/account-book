package com.server.base.repository.categoryRepository;

import com.server.base.repository.userRepository.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
    Optional<Category> findCategoryByCategoryNoAndUser(Long categoryNo, User user);
}