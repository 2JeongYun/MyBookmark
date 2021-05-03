package com.github.neukrang.mybookmark.web.dto.section;

import com.github.neukrang.mybookmark.domain.section.Section;
import com.github.neukrang.mybookmark.web.dto.category.CategoryResponseDto;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SectionResponseDto {

    private Long sId;
    private String sName;
    private List<CategoryResponseDto> categoryList;

    public SectionResponseDto(Section entity) {
        this.sId = entity.getId();
        this.sName = entity.getName();
        this.categoryList = entity.getCategories().stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
    }
}
