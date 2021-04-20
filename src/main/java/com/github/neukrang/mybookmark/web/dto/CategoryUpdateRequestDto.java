package com.github.neukrang.mybookmark.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryUpdateRequestDto {

    private String name;
    private String color;
    private Long sectionId;

    @Builder
    public CategoryUpdateRequestDto (String name, String color, Long sectionId) {
        this.name = name;
        this.color = color;
        this.sectionId = sectionId;
    }
}
