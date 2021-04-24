package com.github.neukrang.mybookmark.web.dto.category;

import com.github.neukrang.mybookmark.domain.category.Category;
import com.github.neukrang.mybookmark.web.dto.bookmark.BookmarkListResponseDto;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CategoryListResponseDto {

    private Long id;
    private String name;
    private String color;
    private int openCount;
    private List<BookmarkListResponseDto> bookmarkList;

    public CategoryListResponseDto(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.color = entity.getColor();
        this.openCount = entity.getOpenCount();
        this.bookmarkList = entity.getBookmarks().stream()
                .map(BookmarkListResponseDto::new)
                .collect(Collectors.toList());
    }
}
