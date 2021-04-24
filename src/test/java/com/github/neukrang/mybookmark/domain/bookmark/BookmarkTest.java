package com.github.neukrang.mybookmark.domain.bookmark;

import com.github.neukrang.mybookmark.domain.category.Category;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookmarkTest {

    @Test
    public void 북마크_생성한다() {
        Category category = new Category();
        String address = "address";
        String alias = "alias";
        String description = "description";
        String color = "color";

        Bookmark result = Bookmark.builder()
                .category(category)
                .address(address)
                .alias(alias)
                .description(description)
                .color(color)
                .build();

        assertThat(result.getCategory()).isEqualTo(category);
        assertThat(result.getAddress()).isEqualTo(address);
        assertThat(result.getAlias()).isEqualTo(alias);
        assertThat(result.getDescription()).isEqualTo(description);
        assertThat(result.getColor()).isEqualTo(color);
        assertThat(result.getOpenCount()).isEqualTo(0);
    }

    @Test
    public void 북마크_수정한다() {
        Category updatedCategory = new Category();
        String updatedAlias = "updatedAlias";
        String updatedColor = "updatedColor";
        String updatedDescription = "updatedDescription";
        Bookmark bookmark = Bookmark.builder()
                .address("address")
                .alias("alias")
                .color("color")
                .description("description")
                .category(new Category())
                .build();

        bookmark.update(updatedCategory, updatedAlias, updatedDescription, updatedColor);

        assertThat(bookmark.getCategory()).isEqualTo(updatedCategory);
        assertThat(bookmark.getAlias()).isEqualTo(updatedAlias);
        assertThat(bookmark.getDescription()).isEqualTo(updatedDescription);
        assertThat(bookmark.getColor()).isEqualTo(updatedColor);
    }

    @Test
    public void 북마크의_카테고리를_변경하면_카테고리에_변경내용이_적용된다() {
        Category prevCategory = new Category();
        Category updatedCategory = new Category();
        Bookmark bookmark = Bookmark.builder()
                .category(prevCategory)
                .build();

        bookmark.update(updatedCategory, "", "", "");

        assertThat(prevCategory.getBookmarks()).isEmpty();
        assertThat(updatedCategory.getBookmarks()).contains(bookmark);
    }

    @Test
    public void 북마크의_열람횟수_증가한다() {
        Bookmark bookmark = Bookmark.builder()
                .category(new Category())
                .build();
        int openRepeatCount = 3;

        for (int i = 0; i < openRepeatCount; i++) {
            bookmark.addOpenCount();
        }

        assertThat(bookmark.getOpenCount()).isEqualTo(openRepeatCount);
    }
}
