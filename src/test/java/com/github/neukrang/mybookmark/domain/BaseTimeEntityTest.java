package com.github.neukrang.mybookmark.domain;

import com.github.neukrang.mybookmark.domain.bookmark.Bookmark;
import com.github.neukrang.mybookmark.domain.bookmark.BookmarkRepository;
import com.github.neukrang.mybookmark.domain.category.Category;
import com.github.neukrang.mybookmark.domain.category.CategoryRepository;
import com.github.neukrang.mybookmark.domain.section.Section;
import com.github.neukrang.mybookmark.domain.section.SectionRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTimeEntityTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @After
    public void clean() {
        bookmarkRepository.deleteAll();
        categoryRepository.deleteAll();
        sectionRepository.deleteAll();
    }

    @Test
    public void 생성시간_저장한다() {
        Section section = sectionRepository.save(Section.builder()
                .name("name")
                .build());
        Category category = categoryRepository.save(Category.builder()
                .name("name")
                .color("color")
                .section(section)
                .build());
        Bookmark bookmark = bookmarkRepository.save(Bookmark.builder()
                .category(category)
                .address("address")
                .color("color")
                .build());

        assertThat(category.getCreatedTime()).isAfter(LocalDateTime.now().minusSeconds(1000));
        assertThat(category.getModifiedTime()).isAfter(LocalDateTime.now().minusSeconds(1000));
        assertThat(bookmark.getCreatedTime()).isAfter(LocalDateTime.now().minusSeconds(1000));
        assertThat(bookmark.getModifiedTime()).isAfter(LocalDateTime.now().minusSeconds(1000));
    }
}
