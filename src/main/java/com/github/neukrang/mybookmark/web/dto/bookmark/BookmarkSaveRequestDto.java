package com.github.neukrang.mybookmark.web.dto.bookmark;

import com.github.neukrang.mybookmark.domain.bookmark.Bookmark;
import com.github.neukrang.mybookmark.domain.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookmarkSaveRequestDto {

    private String address;
    private String alias;
    private String description;
    private String color;
    private Long categoryId;

    @Builder
    public BookmarkSaveRequestDto(String address, String alias, String description, String color, Long categoryId) {
        this.address = address;
        this.alias = alias;
        this.description = description;
        this.color = color;
        this.categoryId = categoryId;
    }

    public Bookmark toEntity(Category category) {
        return Bookmark.builder()
                .address(address)
                .alias(alias)
                .description(description)
                .color(color)
                .category(category)
                .build();
    }
}
