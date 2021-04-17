package com.github.neukrang.mybookmark.web.dto;

import com.github.neukrang.mybookmark.domain.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategorySaveRequestDto {

    private String name;
    private String color;

    @Builder
    public CategorySaveRequestDto (String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Category toEntity() {
        return Category.builder()
                .name(this.name)
                .color(this.color)
                .build();
    }
}
