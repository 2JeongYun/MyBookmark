package com.github.neukrang.mybookmark.domain.bookmark;

import com.github.neukrang.mybookmark.config.TextConfig;
import com.github.neukrang.mybookmark.domain.category.Category;
import com.github.neukrang.mybookmark.domain.category.CategoryRepository;
import com.github.neukrang.mybookmark.domain.section.Section;
import com.github.neukrang.mybookmark.domain.section.SectionRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookmarkRepositoryTest {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @After
    public void clean() {
        bookmarkRepository.deleteAll();
        categoryRepository.deleteAll();
        sectionRepository.deleteAll();
    }

    @Test
    public void 북마크_저장한다() {
        Category category = makeCategory("category1", makeSection("section1"));
        String address = "address1";
        String alias = "alias1";
        String description = "description1";
        Bookmark bookmark = Bookmark.builder()
                .address(address)
                .alias(alias)
                .description(description)
                .category(category)
                .build();

        Long bookmarkId = bookmarkRepository.save(bookmark).getId();

        Bookmark result = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(() -> new IllegalArgumentException(TextConfig.cantFindBookmarkMsg(bookmarkId)));
        assertThat(result.getAddress()).isEqualTo(address);
        assertThat(result.getAlias()).isEqualTo(alias);
        assertThat(result.getDescription()).isEqualTo(description);
        assertThat(result.getCategory()).isEqualToComparingFieldByField(category);
        assertThat(result.getOpenCount()).isEqualTo(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 북마크_삭제한다() {
        Category category = makeCategory("category1", makeSection("section1"));
        Long bookmarkId = bookmarkRepository.save(Bookmark.builder()
                .address("address")
                .category(category)
                .build()).getId();

        bookmarkRepository.deleteById(bookmarkId);

        bookmarkRepository.findById(bookmarkId).
                orElseThrow(() -> new IllegalArgumentException());
    }

    @Test
    public void 북마크_수정한다() {
        Category updatedCategory = makeCategory("category2", makeSection("section2"));
        String updatedAlias = "updatedAlias";
        String updatedDescription = "updatedDescription";
        String updatedColor = "updatedColor";
        Bookmark bookmark = Bookmark.builder()
                .address("address")
                .category(makeCategory("category1", makeSection("section1")))
                .build();
        Long bookmarkId = bookmarkRepository.save(bookmark).getId();

        bookmark.update(updatedCategory, updatedAlias, updatedDescription, updatedColor);

        Bookmark result = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(() -> new IllegalArgumentException());
        assertThat(result.getCategory()).isEqualToComparingFieldByField(updatedCategory);
        assertThat(result.getAlias()).isEqualTo(updatedAlias);
        assertThat(result.getDescription()).isEqualTo(updatedDescription);
        assertThat(result.getColor()).isEqualTo(updatedColor);
    }

    @Test
    public void 북마크_가져온다() {
        Category category = makeCategory("category1", makeSection("section1"));
        String address = "address";
        Long bookmarkId = bookmarkRepository.save(Bookmark.builder()
                .address(address)
                .category(category)
                .build()).getId();

        Bookmark result = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(() -> new IllegalArgumentException());

        assertThat(result.getAddress()).isEqualTo(address);
        assertThat(result.getCategory()).isEqualToComparingFieldByField(category);
    }

    @Test
    public void 북마크의_카테고리를_수정하면_카테고리_객체와_엔티티에도_정상반영된다() {
        Section section = makeSection("section1");
        Category prevCategory = makeCategory("prevCategory", section);
        Category updatedCategory = makeCategory("updatedCategory", section);
        Bookmark bookmark = Bookmark.builder()
                .address("address")
                .category(prevCategory)
                .build();
        Long bookmarkId = bookmarkRepository.save(bookmark).getId();

        bookmark.update(updatedCategory, "", "", "");

        Bookmark result = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(() -> new IllegalArgumentException());
        assertThat(prevCategory.getBookmarks()).isEmpty();
        assertThat(updatedCategory.getBookmarks()).contains(result);
        assertThat(result.getCategory()).isEqualToComparingFieldByField(updatedCategory);
    }

    public Section makeSection(String name) {
        return sectionRepository.save(Section.builder()
                .name(name)
                .build());
    }

    public Category makeCategory(String name, Section section) {
        return categoryRepository.save(Category.builder()
                .name(name)
                .color("test color")
                .section(section)
                .build());
    }
}
