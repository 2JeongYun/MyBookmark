package com.github.neukrang.mybookmark.service;

import com.github.neukrang.mybookmark.config.TextConfig;
import com.github.neukrang.mybookmark.domain.category.Category;
import com.github.neukrang.mybookmark.domain.category.CategoryRepository;
import com.github.neukrang.mybookmark.domain.section.Section;
import com.github.neukrang.mybookmark.web.dto.category.CategoryListResponseDto;
import com.github.neukrang.mybookmark.web.dto.category.CategoryResponseDto;
import com.github.neukrang.mybookmark.web.dto.category.CategorySaveRequestDto;
import com.github.neukrang.mybookmark.web.dto.category.CategoryUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SectionService sectionService;

    public Long saveCategory(CategorySaveRequestDto requestDto) {
        Section section = sectionService.findEntityById(requestDto.getSectionId());
        return categoryRepository.save(requestDto.toEntity(section)).getId();
    }

    public Long updateCategory(Long categoryId, CategoryUpdateRequestDto requestDto) {
        Category category = findEntityById(categoryId);
        Section section = sectionService.findEntityById(requestDto.getSectionId());
        return category.update(section, requestDto.getName(), requestDto.getColor());
    }

    public Long deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
        return categoryId;
    }

    public CategoryResponseDto findById(Long categoryId) {
        return new CategoryResponseDto(findEntityById(categoryId));
    }

    public List<CategoryListResponseDto> findAllDescByOpenCount(Long sectionId) {
        return sectionService.getCategories(sectionId).stream()
                .map(CategoryListResponseDto::new)
                .collect(Collectors.toList());
    }

    public Category findEntityById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(TextConfig.cantFindCategoryMsg(id)));
    }
}
