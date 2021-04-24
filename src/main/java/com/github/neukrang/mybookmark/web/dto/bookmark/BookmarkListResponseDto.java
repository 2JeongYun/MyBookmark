package com.github.neukrang.mybookmark.web.dto.bookmark;

import com.github.neukrang.mybookmark.domain.bookmark.Bookmark;
import lombok.Getter;

@Getter
public class BookmarkListResponseDto {

    private Long id;
    private String address;
    private String alias;
    private String color;
    private int openCount;

    public BookmarkListResponseDto(Bookmark entity) {
        this.id = entity.getId();
        this.address = entity.getAddress();
        this.alias = entity.getAlias();
        this.color = entity.getColor();
        this.openCount = entity.getOpenCount();
    }
}
