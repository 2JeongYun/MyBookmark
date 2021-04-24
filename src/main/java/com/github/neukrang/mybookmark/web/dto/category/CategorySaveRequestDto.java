package com.github.neukrang.mybookmark.web.dto.category;

import com.github.neukrang.mybookmark.domain.category.Category;
import com.github.neukrang.mybookmark.domain.section.Section;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategorySaveRequestDto {

    private Long sectionId;
    private String name;
    private String color;

    @Builder
    public CategorySaveRequestDto(Long sectionId, String name, String color) {
        this.sectionId = sectionId;
        this.name = name;
        this.color = color;
    }

    public Category toEntity(Section section) {
        return Category.builder()
                .section(section)
                .name(name)
                .color(color)
                .build();
    }
}
