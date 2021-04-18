package com.github.neukrang.mybookmark.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.neukrang.mybookmark.domain.bookmark.BookmarkRepository;
import com.github.neukrang.mybookmark.domain.category.Category;
import com.github.neukrang.mybookmark.domain.category.CategoryRepository;
import com.github.neukrang.mybookmark.web.dto.CategorySaveRequestDto;
import com.github.neukrang.mybookmark.web.dto.CategoryUpdateRequestDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @After
    public void clean() {
        bookmarkRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void 카테고리_저장한다() throws Exception {
        String name = "name";
        String color = "color";

        CategorySaveRequestDto requestDto = CategorySaveRequestDto.builder()
                .name(name)
                .color(color)
                .build();

        String url = "http://localhost:" + port + "/api/v1/category";

        MvcResult result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andReturn();

        Long id = Long.parseLong(result.getResponse().getContentAsString());

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다. ID:" + id));
        assertThat(category.getName()).isEqualTo(name);
        assertThat(category.getColor()).isEqualTo(color);
    }

    @Test
    public void 카테고리_전부_가져온다() {
        String[] names = {"name1", "name2", "name3"};
        for (String name : names) {
            categoryRepository.save(Category.builder()
                    .name(name)
                    .color("color")
                    .build());
        }

        assertThat(categoryRepository.count()).isEqualTo(names.length);
    }

    @Test
    public void 카테고리_수정한다() throws Exception {
        Long id = categoryRepository.save(Category.builder()
                .name("name")
                .color("color")
                .build()).getId();

        String name = "updateName";
        String color = "updateColor";

        CategoryUpdateRequestDto requestDto = CategoryUpdateRequestDto.builder()
                .name(name)
                .color(color)
                .build();

        String url = "http://localhost:" + port + "/api/v1/category/" + id;

        mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다. ID:" + id));
        assertThat(category.getName()).isEqualTo(name);
        assertThat(category.getColor()).isEqualTo(color);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 카테고리_삭제한다() throws Exception {
        Long id = categoryRepository.save(Category.builder()
                .name("name")
                .color("color")
                .build()).getId();

        String url = "http://localhost:" + port + "/api/v1/category/" + id;

        mockMvc.perform(delete(url))
                .andExpect(status().isOk());

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
