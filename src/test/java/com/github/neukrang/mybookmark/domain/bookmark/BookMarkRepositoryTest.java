package com.github.neukrang.mybookmark.domain.bookmark;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

// Fixme BookMark 의 Category FK 추가에 따른 테스트 변경
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookMarkRepositoryTest {

    @Autowired
    BookMarkRepository bookMarkRepository;

    @After
    public void clean() {
        bookMarkRepository.deleteAll();
    }

    @Test
    public void testBookMarkRepositorySave() {
        String address = "testAddress";
        String description = "testDescription";
        String color = "testColor";

        bookMarkRepository.save(BookMark.builder()
                .address(address)
                .description(description)
                .color(color)
                .build());

        BookMark bookMark = bookMarkRepository.findAll().get(0);

        assertThat(bookMark.getAddress()).isEqualTo(address);
        assertThat(bookMark.getDescription()).isEqualTo(description);
        assertThat(bookMark.getAlias()).isNull();
        assertThat(bookMark.getColor()).isEqualTo(color);
        assertThat(bookMark.getOpenCount()).isEqualTo(0);
    }
}
