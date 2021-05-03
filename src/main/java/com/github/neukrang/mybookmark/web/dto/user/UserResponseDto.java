package com.github.neukrang.mybookmark.web.dto.user;

import com.github.neukrang.mybookmark.domain.user.User;
import com.github.neukrang.mybookmark.web.dto.section.SectionResponseDto;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserResponseDto {

    private Long uId;
    private String uName;
    private List<SectionResponseDto> sectionList;

    public UserResponseDto(User user) {
        this.uId = user.getId();
        this.uName = user.getName();
        this.sectionList = user.getSections().stream()
                .map(SectionResponseDto::new)
                .collect(Collectors.toList());
    }
}
