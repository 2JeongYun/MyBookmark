package com.github.neukrang.mybookmark.service;

import com.github.neukrang.mybookmark.domain.section.Section;
import com.github.neukrang.mybookmark.domain.section.SectionRepository;
import com.github.neukrang.mybookmark.web.dto.section.SectionSaveRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class SectionServiceTest {

    @Mock
    private SectionRepository sectionRepository;

    @InjectMocks
    private SectionService sectionService;

    @Test
    public void 섹션_저장한다() {
        Long id = 3L;
        Section section = new Section();
        ReflectionTestUtils.setField(section, "id", id);

        SectionSaveRequestDto requestDto = mock(SectionSaveRequestDto.class);
        given(requestDto.toEntity()).willReturn(section);
        given(sectionRepository.save(requestDto.toEntity())).willReturn(section);

        Long result = sectionService.saveSection(requestDto);

        assertThat(result).isEqualTo(id);
    }
}
