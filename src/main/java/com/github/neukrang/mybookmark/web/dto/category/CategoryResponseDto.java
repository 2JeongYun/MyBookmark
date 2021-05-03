package com.github.neukrang.mybookmark.web.dto.category;

import com.github.neukrang.mybookmark.domain.category.Category;
import com.github.neukrang.mybookmark.web.dto.bookmark.BookmarkListResponseDto;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

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
        this.bookmarkList = entity.getBookmarks().stream()
                .map(BookmarkListResponseDto::new)
                .collect(Collectors.toList());
    }
}
