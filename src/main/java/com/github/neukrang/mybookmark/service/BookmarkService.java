package com.github.neukrang.mybookmark.service;

import com.github.neukrang.mybookmark.config.TextConfig;
import com.github.neukrang.mybookmark.domain.bookmark.Bookmark;
import com.github.neukrang.mybookmark.domain.bookmark.BookmarkRepository;
import com.github.neukrang.mybookmark.domain.category.Category;
import com.github.neukrang.mybookmark.domain.category.CategoryRepository;
import com.github.neukrang.mybookmark.web.dto.BookmarkResponseDto;
import com.github.neukrang.mybookmark.web.dto.BookmarkSaveRequestDto;
import com.github.neukrang.mybookmark.web.dto.BookmarkUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Text;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long save(BookmarkSaveRequestDto requestDto) {
        Long categoryId = requestDto.getCategoryId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException(TextConfig.cantFindCategoryMsg(categoryId)));
        return bookmarkRepository.save(requestDto.toEntity(category)).getId();
    }

    @Transactional
    public BookmarkResponseDto findById(Long id) {
        Bookmark bookmark = bookmarkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(TextConfig.cantFindBookmarkMsg(id)));
        return new BookmarkResponseDto(bookmark);
    }

    @Transactional
    public Long update(Long id, BookmarkUpdateRequestDto requestDto) {
        Bookmark bookmark = bookmarkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(TextConfig.cantFindBookmarkMsg(id)));
        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException(TextConfig.
                        cantFindCategoryMsg(requestDto.getCategoryId())));
        bookmark.update(category, requestDto.getAlias(), requestDto.getDescription(), requestDto.getColor());
        return id;
    }
}
