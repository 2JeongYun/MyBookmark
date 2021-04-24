package com.github.neukrang.mybookmark.domain.category;

import com.github.neukrang.mybookmark.domain.bookmark.Bookmark;
import com.github.neukrang.mybookmark.domain.section.Section;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTest {

    @Test
    public void 카테고리_생성한다() {
        Section section = new Section();
        String name = "name";
        String color = "color";

        Category result = Category.builder()
                .name(name)
                .color(color)
                .section(section)
                .build();

        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getColor()).isEqualTo(color);
        assertThat(result.getSection()).isEqualTo(section);
        assertThat(result.getOpenCount()).isEqualTo(0);
    }

    @Test
    public void 카테고리_수정한다() {
        Category category = Category.builder()
                .name("name")
                .color("color")
                .section(new Section())
                .build();
        String updatedName = "updatedName";
        String updatedColor = "updatedColor";
        Section updatedSection = new Section();

        category.update(updatedSection, updatedName, updatedColor);

        assertThat(category.getSection()).isEqualTo(updatedSection);
        assertThat(category.getName()).isEqualTo(updatedName);
        assertThat(category.getColor()).isEqualTo(updatedColor);
    }

    @Test
    public void 카테고리_북마크_추가된다() {
        Category category = new Category();

        category.addBookmark(new Bookmark());

        assertThat(category.getBookmarks()).hasSize(1);
    }

    @Test
    public void 카테고리_북마크_삭제한다() {
        Category category = new Category();
        Bookmark bookmark = new Bookmark();
        category.addBookmark(bookmark);

        category.deleteBookmark(bookmark);

        assertThat(category.getBookmarks()).isEmpty();
    }

    @Test
    public void 카테고리_열람횟수_증가한다() {
        Category category = new Category();
        int openRepeatCount = 3;

        for (int i = 0; i < openRepeatCount; i++) {
            category.addOpenCount();
        }

        assertThat(category.getOpenCount()).isEqualTo(openRepeatCount);
    }

    @Test
    public void 카테고리의_섹션을_변경하면_섹션에_변경내용이_적용된다() {
        Section prevSection = new Section();
        Section updatedSection = new Section();
        Category category = Category.builder()
                .section(prevSection)
                .build();

        category.update(updatedSection, "", "");

        assertThat(prevSection.getCategories()).isEmpty();
        assertThat(updatedSection.getCategories()).contains(category);
    }
}
