package com.github.neukrang.mybookmark.web.dto;

import com.github.neukrang.mybookmark.domain.bookmark.Bookmark;
import lombok.Getter;

@Getter
public class BookmarkResponseDto {

    private Long id;
    private String address;
    private String alias;
    private String description;
    private String color;
    private int openCount;

    public BookmarkResponseDto(Bookmark entity) {
        this.id = entity.getId();
        this.address = entity.getAddress();
        this.alias = entity.getAlias();
        this.description = entity.getDescription();
        this.color = entity.getColor();
        this.openCount = entity.getOpenCount();
    }
}
