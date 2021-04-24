package com.github.neukrang.mybookmark.web;

import com.github.neukrang.mybookmark.service.BookmarkService;
import com.github.neukrang.mybookmark.service.CategoryService;
import com.github.neukrang.mybookmark.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final BookmarkService bookmarkService;
    private final CategoryService categoryService;
    private final SectionService sectionService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("sectionList", sectionService.findAllOrderByName());
        model.addAttribute("category", new ArrayList<>());
        return "home";
    }

    @GetMapping("/{sectionId}")
    public String home(@PathVariable Long sectionId, Model model) {
        model.addAttribute("sectionList", sectionService.findAllOrderByName());
        model.addAttribute("category", categoryService.findAllDescByOpenCount(sectionId));
        model.addAttribute("currentSection", sectionService.findById(sectionId));
        return "home";
    }

    @GetMapping("/section/save")
    public String saveSection() {
        return "section-save";
    }

    @GetMapping("/section/update/{id}")
    public String updateSection(@PathVariable Long id, Model model) {
        model.addAttribute("currentSection", sectionService.findById(id));
        return "section-update";
    }

    @GetMapping("/category/save")
    public String saveCategory(Model model) {
        model.addAttribute("sectionList", sectionService.findAllOrderByName());
        return "category-save";
    }

    @GetMapping("/category/update/{id}")
    public String updateCategory(@PathVariable Long id, Model model) {
        model.addAttribute("currentCategory", categoryService.findById(id));
        model.addAttribute("sectionList", sectionService.findAllOrderByName());
        return "category-update";
    }
}
