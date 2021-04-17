package com.github.neukrang.mybookmark.web.dto;

import com.github.neukrang.mybookmark.domain.category.Category;
import com.github.neukrang.mybookmark.domain.bookmark.Bookmark;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoryResponseDto {

    private Long id;
    private String name;
    private String color;
    private List<Bookmark> bookmarks;
    private int openCount;

    public CategoryResponseDto(Category Entity) {
        this.id = Entity.getId();
        this.name = Entity.getName();
        this.color = Entity.getColor();
        this.bookmarks = Entity.getBookmarks();
        this.openCount = Entity.getOpenCount();
    }
}
