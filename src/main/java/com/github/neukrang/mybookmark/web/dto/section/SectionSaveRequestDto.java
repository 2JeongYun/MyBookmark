package com.github.neukrang.mybookmark.web.dto.section;

import com.github.neukrang.mybookmark.domain.section.Section;
import com.github.neukrang.mybookmark.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SectionSaveRequestDto {

    private Long userId;
    private String name;

    public Section toEntity(User user) {
        return Section.builder()
                .name(name)
                .user(user)
                .build();
    }

    @Builder
    public SectionSaveRequestDto(String name, Long userId) {
        this.userId = userId;
        this.name = name;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
