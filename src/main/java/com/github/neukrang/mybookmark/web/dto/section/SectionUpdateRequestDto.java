package com.github.neukrang.mybookmark.web.dto.section;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SectionUpdateRequestDto {

    private String name;

    @Builder
    public SectionUpdateRequestDto(String name) {
        this.name = name;
    }
}
