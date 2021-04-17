package com.github.neukrang.mybookmark.service;

import com.github.neukrang.mybookmark.domain.category.Category;
import com.github.neukrang.mybookmark.domain.category.CategoryRepository;
import com.github.neukrang.mybookmark.web.dto.CategoryResponseDto;
import com.github.neukrang.mybookmark.web.dto.CategorySaveRequestDto;

import com.github.neukrang.mybookmark.web.dto.CategoryUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long save(CategorySaveRequestDto requestDto) {
        return categoryRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<CategoryResponseDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoryResponseDto findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다. ID:" + id));
        return new CategoryResponseDto(category);
    }

    @Transactional
    public Long update(Long id, CategoryUpdateRequestDto requestDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        category.update(requestDto.getName(), requestDto.getColor());
        return id;
    }

    @Transactional
    public Long delete(Long id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다 ID:" + id));
        categoryRepository.deleteById(id);
        return id;
    }
}
