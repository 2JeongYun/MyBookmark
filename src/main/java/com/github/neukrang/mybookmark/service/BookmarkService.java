package com.github.neukrang.mybookmark.service;

import com.github.neukrang.mybookmark.config.TextConfig;
import com.github.neukrang.mybookmark.domain.bookmark.Bookmark;
import com.github.neukrang.mybookmark.domain.bookmark.BookmarkRepository;
import com.github.neukrang.mybookmark.domain.category.Category;
import com.github.neukrang.mybookmark.web.dto.bookmark.BookmarkSaveRequestDto;
import com.github.neukrang.mybookmark.web.dto.bookmark.BookmarkUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final CategoryService categoryService;

    public Long saveBookmark(BookmarkSaveRequestDto requestDto) {
        Category category = categoryService.findEntityById(requestDto.getCategoryId());
        if (requestDto.getAlias().trim().equals("")) {
            requestDto.setAlias(requestDto.getAddress());
        }
        return bookmarkRepository.save(requestDto.toEntity(category)).getId();
    }

    public Long updateBookmark(Long bookmarkId, BookmarkUpdateRequestDto requestDto) {
        Category category = categoryService.findEntityById(requestDto.getCategoryId());
        Bookmark bookmark = findEntityById(bookmarkId);
        bookmark.update(category, requestDto.getAlias(), requestDto.getDescription(), requestDto.getColor());
        return bookmarkId;
    }

    public Long deleteBookmark(Long bookmarkId) {
        Bookmark bookmark = findEntityById(bookmarkId);
        return bookmarkId;
    }

    protected Bookmark findEntityById(Long id) {
        return bookmarkRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(TextConfig.cantFindBookmarkMsg(id)));
    }

    public int addOpenCount(Long bookmarkId) {
        Bookmark bookmark = findEntityById(bookmarkId);
        bookmark.addOpenCount();
        return bookmark.getOpenCount();
    }
}
