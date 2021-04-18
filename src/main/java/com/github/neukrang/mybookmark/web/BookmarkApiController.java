package com.github.neukrang.mybookmark.web;

import com.github.neukrang.mybookmark.service.BookmarkService;
import com.github.neukrang.mybookmark.web.dto.BookmarkResponseDto;
import com.github.neukrang.mybookmark.web.dto.BookmarkSaveRequestDto;
import com.github.neukrang.mybookmark.web.dto.BookmarkUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BookmarkApiController {

    private final BookmarkService bookmarkService;

    // FIXME need implement
    @PostMapping("/api/v1/bookmark")
    public Long save(@RequestBody BookmarkSaveRequestDto requestDto) {
        return bookmarkService.save(requestDto);
    }

    @GetMapping("/api/v1/bookmark/{id}")
    public BookmarkResponseDto findById(@PathVariable Long id) {
        return bookmarkService.findById(id);
    }

    @PutMapping("/api/v1/bookmark/{id}")
    public Long update(@PathVariable Long id, @RequestBody BookmarkUpdateRequestDto requestDto) {
        return bookmarkService.update(id, requestDto);
    }
}
