package com.github.neukrang.mybookmark.web;

import com.github.neukrang.mybookmark.service.BookmarkService;
import com.github.neukrang.mybookmark.web.dto.bookmark.BookmarkSaveRequestDto;
import com.github.neukrang.mybookmark.web.dto.bookmark.BookmarkUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BookmarkApiController {

    private final BookmarkService bookmarkService;

    @PostMapping("/api/v1/bookmark")
    public Long saveBookmark(@RequestBody BookmarkSaveRequestDto requestDto) {
        return bookmarkService.saveBookmark(requestDto);
    }

    @PutMapping("/api/v1/bookmark/{bookmarkId}")
    public Long updateBookmark(@PathVariable Long bookmarkId, @RequestBody BookmarkUpdateRequestDto requestDto) {
        return bookmarkService.updateBookmark(bookmarkId, requestDto);
    }

    @DeleteMapping("/api/v1/bookmark/{bookmarkId}")
    public Long deleteBookmark(@PathVariable Long bookmarkId) {
        return bookmarkService.deleteBookmark(bookmarkId);
    }

    @PutMapping("/api/v1/bookmark/addcount/{bookmarkId}")
    public int addOpenCount(@PathVariable Long bookmarkId) {
        return bookmarkService.addOpenCount(bookmarkId);
    }
}
