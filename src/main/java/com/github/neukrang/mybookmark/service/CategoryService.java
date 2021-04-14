package com.github.neukrang.mybookmark.service;

import com.github.neukrang.mybookmark.domain.category.CategoryRepository;
import com.github.neukrang.mybookmark.web.dto.CategoryResponseDto;
import com.github.neukrang.mybookmark.web.dto.CategorySaveRequestDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long save(CategorySaveRequestDto requestDto) {
        return categoryRepository.save(requestDto.toEntity()).getId();
    }
}
