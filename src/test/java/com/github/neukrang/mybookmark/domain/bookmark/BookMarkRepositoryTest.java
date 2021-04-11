package com.github.neukrang.mybookmark.domain.bookmark;

import com.github.neukrang.mybookmark.domain.Category.Category;
import com.github.neukrang.mybookmark.domain.Category.CategoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookMarkRepositoryTest {

    @Autowired
    private BookMarkRepository bookMarkRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @After
    public void clean() {
        bookMarkRepository.deleteAll();
    }

    @Test
    public void testBookMarkRepositorySave() {
        String address = "testAddress";
        String description = "testDescription";
        String color = "testColor";

        String categoryName = "testCategory";
        String categoryColor = "testColor";

        Category category = setTestCategory(categoryName, categoryColor);

        bookMarkRepository.save(BookMark.builder()
                .category(category)
                .address(address)
                .description(description)
                .color(color)
                .build());

        BookMark bookMark = bookMarkRepository.findAll().get(0);

        assertThat(bookMark.getAddress()).isEqualTo(address);
        assertThat(bookMark.getDescription()).isEqualTo(description);
        assertThat(bookMark.getAlias()).isNull();
        assertThat(bookMark.getColor()).isEqualTo(color);
        assertThat(bookMark.getCategory()).isEqualToComparingFieldByField(category);
        assertThat(bookMark.getOpenCount()).isEqualTo(0);
    }

    public Category setTestCategory(String name, String color) {
        return categoryRepository.save(Category.builder()
                .name(name)
                .color(color)
                .build());
    }
}
