package com.github.neukrang.mybookmark.web.dto.bookmark;

import com.github.neukrang.mybookmark.domain.bookmark.Bookmark;
import lombok.Getter;

@Getter
public class BookmarkResponseDto {

    private Long bId;
    private String bAddress;
    private String bAlias;
    private String bDescription;
    private String bColor;
    private int bOpenCount;

    public BookmarkResponseDto(Bookmark entity) {
        this.bId = entity.getId();
        this.bAddress = entity.getAddress();
        this.bAlias = entity.getAlias();
        this.bDescription = entity.getDescription();
        this.bColor = entity.getColor();
        this.bOpenCount = entity.getOpenCount();
    }
}
