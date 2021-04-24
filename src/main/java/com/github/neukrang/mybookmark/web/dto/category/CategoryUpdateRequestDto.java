package com.github.neukrang.mybookmark.web.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryUpdateRequestDto {

    private Long sectionId;
    private String name;
    private String color;

    @Builder
    public CategoryUpdateRequestDto(Long sectionId, String name, String color) {
        this.sectionId = sectionId;
        this.name = name;
        this.color = color;
    }
}
