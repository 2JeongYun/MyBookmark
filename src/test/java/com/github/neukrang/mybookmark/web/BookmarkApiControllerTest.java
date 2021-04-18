package com.github.neukrang.mybookmark.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.neukrang.mybookmark.config.TextConfig;
import com.github.neukrang.mybookmark.domain.bookmark.Bookmark;
import com.github.neukrang.mybookmark.domain.bookmark.BookmarkRepository;
import com.github.neukrang.mybookmark.domain.category.Category;
import com.github.neukrang.mybookmark.domain.category.CategoryRepository;
import com.github.neukrang.mybookmark.web.dto.BookmarkSaveRequestDto;
import com.github.neukrang.mybookmark.web.dto.BookmarkUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookmarkApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @After
    public void clean() {
        bookmarkRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    @Transactional
    public void 북마크_저장한다() throws Exception {
        Long categoryId = makeTestCategory("test1").getId();

        String address = "address";
        String color = "color";
        BookmarkSaveRequestDto requestDto = BookmarkSaveRequestDto.builder()
                .address(address)
                .color(color)
                .categoryId(categoryId)
                .build();

        String url = "http://localhost:" + port + "/api/v1/bookmark";

        MvcResult mvcResult = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andReturn();

        Long id = Long.parseLong(mvcResult.getResponse().getContentAsString());
        Bookmark savedBookmark = bookmarkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(TextConfig.cantFindBookmarkMsg(id)));
        assertThat(savedBookmark.getAddress()).isEqualTo(address);
        assertThat(savedBookmark.getColor()).isEqualTo(color);
    }

    @Test
    public void 북마크_수정한다() throws Exception {
        Category category1 = makeTestCategory("test1");
        Long bookmarkId = bookmarkRepository.save(Bookmark.builder()
                .address("address")
                .alias("alias")
                .category(category1)
                .build()).getId();
        Long category2Id = makeTestCategory("test2").getId();
        String alias = "update";
        BookmarkUpdateRequestDto requestDto = BookmarkUpdateRequestDto.builder()
                .alias(alias)
                .categoryId(category2Id)
                .build();

        String url = "http://localhost:" + port + "/api/v1/bookmark/" + bookmarkId;
        mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        Bookmark updatedBookmark = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(() -> new IllegalArgumentException(TextConfig.cantFindBookmarkMsg(bookmarkId)));
        assertThat(updatedBookmark.getAlias()).isEqualTo(alias);
        assertThat(updatedBookmark.getCategory().getId()).isEqualTo(category2Id);
    }

    public Category makeTestCategory(String name) {
        return categoryRepository.save(Category.builder()
                .name(name)
                .color("color")
                .build());
    }
}
