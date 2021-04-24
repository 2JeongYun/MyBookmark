package com.github.neukrang.mybookmark.web.dto.section;

import com.github.neukrang.mybookmark.domain.section.Section;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SectionSaveRequestDto {

    private String name;

    @Builder
    public SectionSaveRequestDto(String name) {
        this.name = name;
    }

    public Section toEntity() {
        return Section.builder()
                .name(name)
                .build();
    }
}
