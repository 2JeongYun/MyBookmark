package com.github.neukrang.mybookmark.web;

import com.github.neukrang.mybookmark.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BookmarkApiController {

    private final BookmarkService bookmarkService;

    // FIXME need implement
    @PostMapping("/api/v1/bookmark/")
    public Long save() {
        return 0L;
    }
}
