package com.server.base.repository.categoryRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
    Optional<Category> findCategoryByCategoryNo(Long categoryNo);
    void deleteByCategoryNo(Long categoryNo);
}