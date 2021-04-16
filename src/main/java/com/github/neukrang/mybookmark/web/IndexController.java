package com.github.neukrang.mybookmark.web;

import com.github.neukrang.mybookmark.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
