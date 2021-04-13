package com.github.neukrang.mybookmark.web;

import com.github.neukrang.mybookmark.service.CategoryService;
import com.github.neukrang.mybookmark.web.dto.CategorySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CategoryApiController {

    private final CategoryService categoryService;

    // FIXME need implement
    @PostMapping("/api/v1/category")
    public Long save(@RequestBody CategorySaveRequestDto requestDto) {
        return categoryService.save(requestDto);
    }
}
