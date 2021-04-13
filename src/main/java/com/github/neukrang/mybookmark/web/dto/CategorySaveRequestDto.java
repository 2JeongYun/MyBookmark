package com.github.neukrang.mybookmark.web.dto;

import com.github.neukrang.mybookmark.domain.Category.Category;
import lombok.Getter;

@Getter
public class CategorySaveRequestDto {

    private String name;
    private String color;

    public Category toEntity() {
        return Category.builder()
                .name(this.name)
                .color(this.color)
                .build();
    }
}
