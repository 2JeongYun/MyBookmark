package com.github.neukrang.mybookmark.web;

import com.github.neukrang.mybookmark.service.CategoryService;
import com.github.neukrang.mybookmark.web.dto.CategoryResponseDto;
import com.github.neukrang.mybookmark.web.dto.CategorySaveRequestDto;
import com.github.neukrang.mybookmark.web.dto.CategoryUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoryApiController {

    private final CategoryService categoryService;

    // FIXME need implement
    @PostMapping("/api/v1/category")
    public Long save(@RequestBody CategorySaveRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @GetMapping("/api/v1/category")
    public List<CategoryResponseDto> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/api/v1/category/{id}")
    public CategoryResponseDto findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PutMapping("/api/v1/category/{id}")
    public Long update(@PathVariable Long id, @RequestBody CategoryUpdateRequestDto requestDto) {
        return categoryService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/category/{id}")
    public Long delete(@PathVariable Long id) {
        return categoryService.delete(id);
    }
}
