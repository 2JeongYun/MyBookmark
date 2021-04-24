package com.github.neukrang.mybookmark.web;

import com.github.neukrang.mybookmark.service.CategoryService;
import com.github.neukrang.mybookmark.web.dto.category.CategorySaveRequestDto;
import com.github.neukrang.mybookmark.web.dto.category.CategoryUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CategoryApiController {

    private final CategoryService categoryService;

    @PostMapping("/api/v1/category")
    public Long saveCategory(@RequestBody CategorySaveRequestDto requestDto) {
        System.out.println("-------" + requestDto.getSectionId());
        return categoryService.saveCategory(requestDto);
    }

    @PutMapping("/api/v1/category/{categoryId}")
    public Long updateCategory(@PathVariable Long categoryId, @RequestBody CategoryUpdateRequestDto requestDto) {
        return categoryService.updateCategory(categoryId, requestDto);
    }

    @DeleteMapping("/api/v1/category/{categoryId}")
    public Long deleteCategory(@PathVariable Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
