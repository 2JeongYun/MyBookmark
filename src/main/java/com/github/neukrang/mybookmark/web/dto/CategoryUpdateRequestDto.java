package com.github.neukrang.mybookmark.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryUpdateRequestDto {

    private String name;
    private String color;

    @Builder
    public CategoryUpdateRequestDto (String name, String color) {
        this.name = name;
        this.color = color;
    }
}
