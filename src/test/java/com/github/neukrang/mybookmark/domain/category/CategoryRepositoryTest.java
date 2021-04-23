package com.github.neukrang.mybookmark.domain.category;

import com.github.neukrang.mybookmark.domain.bookmark.Bookmark;
import com.github.neukrang.mybookmark.domain.section.Section;
import com.github.neukrang.mybookmark.domain.section.SectionRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @After
    public void clean() {
        categoryRepository.deleteAll();
        sectionRepository.deleteAll();
    }

    @Test
    public void 카테고리_저장한다() {
        String name = "category";
        String color = "color";
        Section section = makeSection("section");
        Category category = Category.builder()
                .name(name)
                .color(color)
                .section(section)
                .build();

        Long categoryId = categoryRepository.save(category).getId();

        Category result = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException());
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getColor()).isEqualTo(color);
        assertThat(result.getSection()).isEqualToComparingFieldByField(section);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 카테고리_삭제한다() {
        Category category = Category.builder()
                .name("name")
                .color("color")
                .section(makeSection("section"))
                .build();
        Long categoryId = categoryRepository.save(category).getId();

        categoryRepository.deleteById(categoryId);

        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @Test
    public void 카테고리_수정한다() {
        String updatedName = "updatedName";
        String updatedColor = "updatedColor";
        Section updatedSection = makeSection("section2");
        Category category = Category.builder()
                .name("name")
                .color("color")
                .section(makeSection("section1"))
                .build();
        Long categoryId = categoryRepository.save(category).getId();

        category.update(updatedSection, updatedName, updatedColor);

        Category result = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException());
        assertThat(result.getName()).isEqualTo(updatedName);
        assertThat(result.getColor()).isEqualTo(updatedColor);
        assertThat(result.getSection()).isEqualToComparingFieldByField(updatedSection);
    }

    @Test
    public void 카테고리_가져온다() {
        Section section = makeSection("section");
        String name = "name";
        String color = "color";
        Category category = Category.builder()
                .name(name)
                .color(color)
                .section(section)
                .build();

        Long categoryId = categoryRepository.save(category).getId();

        Category result = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException());
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getColor()).isEqualTo(color);
        assertThat(result.getSection()).isEqualToComparingFieldByField(section);
    }

    @Test
    public void 카테고리의_섹션을_수정하면_섹션개체와_엔티티에도_정상반영된다() {
        Section prevSection = makeSection("prevSection");
        Section updatedSection = makeSection("updatedSection");
        Category category = Category.builder()
                .name("category")
                .color("color")
                .section(prevSection)
                .build();
        Long categoryId = categoryRepository.save(category).getId();

        category.update(updatedSection, "", "");

        Category result = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException());
        assertThat(result.getSection()).isEqualToComparingFieldByField(updatedSection);
        assertThat(result.getSection().getCategories()).contains(result);
        assertThat(prevSection.getCategories()).isEmpty();
    }

    public Section makeSection(String name) {
        return sectionRepository.save(Section.builder()
                .name(name)
                .build());
    }
}
