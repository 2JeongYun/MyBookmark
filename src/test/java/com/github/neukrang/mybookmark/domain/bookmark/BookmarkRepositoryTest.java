package com.github.neukrang.mybookmark.domain.bookmark;

import com.github.neukrang.mybookmark.domain.Category.Category;
import com.github.neukrang.mybookmark.domain.Category.CategoryRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookmarkRepositoryTest {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @After
    public void clean() {
        bookmarkRepository.deleteAll();
    }

    @Transactional
    @Test
    public void testBookmarkRepositorySave() {
        String address = "testAddress";
        String description = "testDescription";
        String color = "testColor";

        Category category = makeTestCategory();

        bookmarkRepository.save(Bookmark.builder()
                .category(category)
                .address(address)
                .description(description)
                .color(color)
                .build());

        Bookmark bookmark = bookmarkRepository.findAll().get(0);

        assertThat(bookmark.getAddress()).isEqualTo(address);
        assertThat(bookmark.getDescription()).isEqualTo(description);
        assertThat(bookmark.getAlias()).isNull();
        assertThat(bookmark.getColor()).isEqualTo(color);
        assertThat(bookmark.getCategory()).isEqualToComparingFieldByField(category);
        assertThat(bookmark.getOpenCount()).isEqualTo(0);
    }

    public Category makeTestCategory() {
        return categoryRepository.save(Category.builder()
                .name("test name")
                .color("test color")
                .build());
    }
}
