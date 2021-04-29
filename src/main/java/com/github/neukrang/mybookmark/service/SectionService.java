package com.github.neukrang.mybookmark.service;

import com.github.neukrang.mybookmark.config.TextConfig;
import com.github.neukrang.mybookmark.domain.category.Category;
import com.github.neukrang.mybookmark.domain.section.Section;
import com.github.neukrang.mybookmark.domain.section.SectionRepository;
import com.github.neukrang.mybookmark.web.dto.section.SectionResponseDto;
import com.github.neukrang.mybookmark.web.dto.section.SectionSaveRequestDto;
import com.github.neukrang.mybookmark.web.dto.section.SectionUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class SectionService {

    private final SectionRepository sectionRepository;
    private final UserService userService;

    public Long saveSection(Long userId, SectionSaveRequestDto requestDto) {
        Section section = requestDto.toEntity(userService.findEntityById(userId));
        return sectionRepository.save(section).getId();
    }

    public Long updateSection(Long sectionId, SectionUpdateRequestDto requestDto) {
        Section section = findEntityById(sectionId);
        section.update(requestDto.getName());
        return sectionId;
    }

    public Long deleteSection(Long sectionId) {
        Section section = findEntityById(sectionId);
        sectionRepository.deleteById(sectionId);
        return sectionId;
    }

    public Section findEntityById(Long sectionId) {
        return sectionRepository.findById(sectionId)
                .orElseThrow(() -> new IllegalArgumentException(TextConfig.cantFindSectionMsg(sectionId)));
    }

    public List<Category> getCategories(Long sectionId) {
        return findEntityById(sectionId).getCategories();
    }

    public SectionResponseDto findById(Long sectionId) {
        Section section = findEntityById(sectionId);
        return new SectionResponseDto(section);
    }

    public List<SectionResponseDto> findAllByUserId(Long userId) {
        return userService.getSections(userId).stream()
                .map(SectionResponseDto::new)
                .collect(Collectors.toList());
    }
}
