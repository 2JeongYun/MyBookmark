package com.github.neukrang.mybookmark.web.dto.section;

import com.github.neukrang.mybookmark.domain.section.Section;
import lombok.Getter;

@Getter
public class SectionResponseDto {

    private Long id;
    private String name;

    public SectionResponseDto(Section entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
