package com.github.neukrang.mybookmark.web;

import com.github.neukrang.mybookmark.config.auth.LoginUser;
import com.github.neukrang.mybookmark.config.auth.dto.SessionUser;
import com.github.neukrang.mybookmark.service.SectionService;
import com.github.neukrang.mybookmark.web.dto.section.SectionSaveRequestDto;
import com.github.neukrang.mybookmark.web.dto.section.SectionUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SectionApiController {

    private final SectionService sectionService;

    @PostMapping("/api/v1/section")
    public Long saveSection(@LoginUser SessionUser user, @RequestBody SectionSaveRequestDto requestDto) {
        return sectionService.saveSection(user.getId(), requestDto);
    }

    @PutMapping("/api/v1/section/{sectionId}")
    public Long updateSection(@PathVariable Long sectionId, @RequestBody SectionUpdateRequestDto requestDto) {
        return sectionService.updateSection(sectionId, requestDto);
    }

    @DeleteMapping("/api/v1/section/{sectionId}")
    public Long deleteSection(@PathVariable Long sectionId) {
        return sectionService.deleteSection(sectionId);
    }
}