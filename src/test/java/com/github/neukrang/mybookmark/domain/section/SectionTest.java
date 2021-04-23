package com.github.neukrang.mybookmark.domain.section;

import com.github.neukrang.mybookmark.domain.category.Category;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SectionTest {

    @Test
    public void 섹션_생성한다() {
        String name = "name";

        Section section = Section.builder()
                .name(name)
                .build();

        assertThat(section.getName()).isEqualTo(name);
    }

    @Test
    public void 섹션_수정한다() {
        Section section = Section.builder()
                .name("name")
                .build();
        String updatedName = "updatedName";

        section.update(updatedName);

        assertThat(section.getName()).isEqualTo(updatedName);
    }

    @Test
    public void 섹션의_카테고리_추가된다() {
        Section section = new Section();

        section.addCategory(new Category());

        assertThat(section.getCategories()).hasSize(1);
    }

    @Test
    public void 섹션의_카테고리_삭제된다() {
        Category category = new Category();
        Section section = new Section();
        section.addCategory(category);

        section.deleteCategory(category);

        assertThat(section.getCategories()).isEmpty();
    }
}
