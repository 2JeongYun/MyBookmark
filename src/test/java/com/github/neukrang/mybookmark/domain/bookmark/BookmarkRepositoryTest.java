package com.github.neukrang.mybookmark.domain.bookmark;

import com.github.neukrang.mybookmark.config.TextConfig;
import com.github.neukrang.mybookmark.domain.category.Category;
import com.github.neukrang.mybookmark.domain.category.CategoryRepository;
import com.github.neukrang.mybookmark.web.dto.BookmarkUpdateRequestDto;
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
public class BookmarkRepositoryTest {

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
    public void 북마크_저장한다() {
        String address = "testAddress";
        String description = "testDescription";
        String color = "testColor";

        Category category = makeTestCategory("test1");

        Long bookmarkId = bookmarkRepository.save(Bookmark.builder()
                .category(category)
                .address(address)
                .description(description)
                .color(color)
                .build()).getId();

        Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(() -> new IllegalArgumentException(TextConfig.cantFindBookmarkMsg(bookmarkId)));

        assertThat(bookmark.getAddress()).isEqualTo(address);
        assertThat(bookmark.getDescription()).isEqualTo(description);
        assertThat(bookmark.getAlias()).isNull();
        assertThat(bookmark.getColor()).isEqualTo(color);
        assertThat(bookmark.getCategory()).isEqualToComparingFieldByField(category);
        assertThat(bookmark.getOpenCount()).isEqualTo(0);
    }

    public Category makeTestCategory(String name) {
        return categoryRepository.save(Category.builder()
                .name(name)
                .color("test color")
                .build());
    }
}
