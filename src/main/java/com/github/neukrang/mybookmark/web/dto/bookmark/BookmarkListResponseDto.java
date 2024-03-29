package com.github.neukrang.mybookmark.web.dto.bookmark;

import com.github.neukrang.mybookmark.domain.bookmark.Bookmark;
import lombok.Getter;

@Getter
public class BookmarkListResponseDto {

    private Long bId;
    private String bAddress;
    private String bAlias;
    private String bColor;
    private int bOpenCount;

    public BookmarkListResponseDto(Bookmark entity) {
        this.bId = entity.getId();
        this.bAddress = entity.getAddress();
        this.bAlias = entity.getAlias();
        this.bColor = entity.getColor();
        this.bOpenCount = entity.getOpenCount();
    }
}
