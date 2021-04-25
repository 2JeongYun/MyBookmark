package com.github.neukrang.mybookmark.web.dto.category;

import com.github.neukrang.mybookmark.domain.category.Category;
import com.github.neukrang.mybookmark.web.dto.bookmark.BookmarkListResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoryResponseDto {

    private Long cId;
    private String cName;
    private String cColor;
    private int cOpenCount;
    private List<BookmarkListResponseDto> bookmarkList;

    public CategoryResponseDto(Category entity) {
        this.cId = entity.getId();
        this.cName = entity.getName();
        this.cColor = entity.getColor();
        this.cOpenCount = entity.getOpenCount();
    }

    public CategoryResponseDto setBookmarkList(List<BookmarkListResponseDto> bookmarkList) {
        this.bookmarkList = bookmarkList;
        return this;
    }
}
