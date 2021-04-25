package com.github.neukrang.mybookmark.web.dto.bookmark;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BookmarkUpdateRequestDto {

    private String alias;
    private String description;
    private String color;
    private Long categoryId;

    @Builder
    public BookmarkUpdateRequestDto(String alias, String description, String color, Long categoryId) {
        this.alias = alias;
        this.description = description;
        this.color = color;
        this.categoryId = categoryId;
    }
}
