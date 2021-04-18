package com.github.neukrang.mybookmark.web.dto;

import com.github.neukrang.mybookmark.domain.bookmark.Bookmark;
import com.github.neukrang.mybookmark.domain.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookmarkSaveRequestDto {

    private Long categoryId;
    private String address;
    private String alias;
    private String description;
    private String color;

    public Bookmark toEntity(Category category) {
        return Bookmark.builder()
                .address(address)
                .alias(alias)
                .description(description)
                .color(color)
                .category(category)
                .build();
    }

    @Builder
    public BookmarkSaveRequestDto(Long categoryId, String address, String alias, String description, String color) {
        this.categoryId = categoryId;
        this.address = address;
        this.alias = alias;
        this.description = description;
        this.color = color;
    }
}
