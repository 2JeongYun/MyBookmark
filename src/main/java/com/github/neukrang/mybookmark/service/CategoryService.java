package com.github.neukrang.mybookmark.service;

import com.github.neukrang.mybookmark.domain.Category.CategoryRepository;
import com.github.neukrang.mybookmark.web.dto.CategorySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Long save(CategorySaveRequestDto requestDto) {
        return categoryRepository.save(requestDto.toEntity()).getId();
    }
}
