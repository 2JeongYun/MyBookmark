package com.github.neukrang.mybookmark.web;

import com.github.neukrang.mybookmark.config.auth.LoginUser;
import com.github.neukrang.mybookmark.config.auth.dto.SessionUser;
import com.github.neukrang.mybookmark.service.CategoryService;
import com.github.neukrang.mybookmark.service.SectionService;
import com.github.neukrang.mybookmark.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final CategoryService categoryService;
    private final SectionService sectionService;
    private final UserService userService;

    @GetMapping("/")
    public String home(@LoginUser SessionUser user, Model model) {
        if (user != null) {
            model.addAttribute("user", userService.findById(user.getId()));
        }
        return "home";
    }

    @GetMapping("/{sectionId}")
    public String home(@LoginUser SessionUser user, @PathVariable Long sectionId, Model model) {
        model.addAttribute("user", userService.findById(user.getId()));
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
    public String saveCategory(@LoginUser SessionUser user, Model model) {
        model.addAttribute("sectionList", sectionService.findAllByUserId(user.getId()));
        return "category-save";
    }

    @GetMapping("/category/update/{id}")
    public String updateCategory(@LoginUser SessionUser user, @PathVariable Long id, Model model) {
        model.addAttribute("currentCategory", categoryService.findById(id));
        model.addAttribute("sectionList", sectionService.findAllByUserId(user.getId()));
        return "category-update";
    }

    @GetMapping("/bookmark/save")
    public String saveBookmark(@RequestParam("prevSectionId") Long sectionId, Model model) {
        model.addAttribute("categoryList", categoryService.findAllBySectionId(sectionId));
        return "bookmark-save";
    }
}
