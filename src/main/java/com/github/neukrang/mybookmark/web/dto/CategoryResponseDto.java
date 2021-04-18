package com.github.neukrang.mybookmark.web.dto;

import com.github.neukrang.mybookmark.domain.category.Category;
import lombok.Getter;

@Getter
public class CategoryResponseDto {

    private Long id;
    private String name;
    private String color;
    private int openCount;

    public CategoryResponseDto(Category Entity) {
        this.id = Entity.getId();
        this.name = Entity.getName();
        this.color = Entity.getColor();
        this.openCount = Entity.getOpenCount();
    }
}
