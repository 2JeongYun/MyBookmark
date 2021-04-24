package com.github.neukrang.mybookmark.domain.category;

import com.github.neukrang.mybookmark.web.dto.category.CategoryListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c ORDER BY c.openCount DESC")
    List<CategoryListResponseDto> findAllDescByOpenCount();
}
