package com.github.neukrang.mybookmark.domain.category;

import com.github.neukrang.mybookmark.domain.bookmark.Bookmark;
import com.github.neukrang.mybookmark.domain.bookmark.BookmarkRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @After
    public void clean() {
        bookmarkRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    public void 북마크_카테고리_연관된다() {
        Category category = categoryRepository.save(Category.builder()
                .name("test category")
                .color("test color")
                .build());

        int bookmarkCount = 2;
        bookmarkRepository.save(Bookmark.builder()
                .address("test1")
                .category(category)
                .alias("test1")
                .color("test1")
                .description("test1")
                .build());
        bookmarkRepository.save(Bookmark.builder()
                .address("test2")
                .category(category)
                .alias("test1")
                .color("test1")
                .description("test1")
                .build());

        saveMockCategory(categoryRepository, bookmarkRepository);

        categoryRepository.findById(category.getId());
        assertThat(category.getBookmarks().size()).isEqualTo(bookmarkCount);
    }

    public void saveMockCategory(CategoryRepository categoryRepository, BookmarkRepository bookmarkRepository) {
        Category category = categoryRepository.save(Category.builder()
                .name("Mock")
                .color("Mock")
                .build());

        bookmarkRepository.save(Bookmark.builder()
                .category(category)
                .address("Mock")
                .build());
    }
}
