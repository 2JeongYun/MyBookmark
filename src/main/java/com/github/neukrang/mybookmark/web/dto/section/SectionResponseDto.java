package com.github.neukrang.mybookmark.web.dto.section;

import com.github.neukrang.mybookmark.domain.section.Section;
import lombok.Getter;

@Getter
public class SectionResponseDto {

    private Long sId;
    private String sName;

    public SectionResponseDto(Section entity) {
        this.sId = entity.getId();
        this.sName = entity.getName();
    }
}
