package com.github.neukrang.mybookmark.web;

import com.github.neukrang.mybookmark.domain.bookmark.BookMark;
import com.github.neukrang.mybookmark.service.BookMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BookMarkApiController {

    private final BookMarkService bookMarkService;

    // FIXME need implement
    @PostMapping("/api/v1/bookmark/")
    public Long save() {
        return 0L;
    }
}
