package com.github.neukrang.mybookmark.web.dto;

import com.github.neukrang.mybookmark.domain.category.Category;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CategoryResponseDto {

    private Long id;
    private String name;
    private String color;
    private List<BookmarkResponseDto> bookmarkResponseDtoList;
    private int openCount;

    public CategoryResponseDto(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.color = entity.getColor();
        this.openCount = entity.getOpenCount();
        this.bookmarkResponseDtoList = entity.getBookmarks().stream()
                .map(BookmarkResponseDto::new)
                .collect(Collectors.toList());
    }
}
