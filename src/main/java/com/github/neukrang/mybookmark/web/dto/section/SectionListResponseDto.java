package com.github.neukrang.mybookmark.web.dto.section;

import com.github.neukrang.mybookmark.domain.section.Section;
import lombok.Getter;

@Getter
public class SectionListResponseDto {

    private Long id;
    private String name;

    public SectionListResponseDto(Section entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
