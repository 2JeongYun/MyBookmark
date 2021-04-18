package com.github.neukrang.mybookmark.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookmarkUpdateRequestDto {

    private String alias;
    private String description;
    private Long categoryId;
    private String color;

    @Builder
    public BookmarkUpdateRequestDto(String alias, String description, Long categoryId, String color) {
        this.alias = alias;
        this.description = description;
        this.categoryId = categoryId;
        this.color = color;
    }
}
