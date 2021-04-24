package com.github.neukrang.mybookmark.domain.section;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SectionRepositoryTest {

    @Autowired
    private SectionRepository sectionRepository;

    @Test
    public void 섹션_저장한다() {
        String name = "name";
        Section section = Section.builder()
                .name(name)
                .build();

        Long sectionId = sectionRepository.save(section).getId();

        Section result = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new IllegalArgumentException());
        assertThat(result.getName()).isEqualTo(name);
    }

    @Test
    public void 섹션_수정한다() {
        String updatedName = "updatedName";
        Section section = Section.builder()
                .name("name")
                .build();
        Long sectionId = sectionRepository.save(section).getId();

        section.update(updatedName);

        Section result = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new IllegalArgumentException());
        assertThat(result.getName()).isEqualTo(updatedName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 섹션_삭제한다() {
        Section section = Section.builder()
                .name("name")
                .build();
        Long sectionId = sectionRepository.save(section).getId();

        sectionRepository.deleteById(sectionId);

        sectionRepository.findById(sectionId)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @Test
    public void 섹션_가져온다() {
        String name = "name";
        Section section = Section.builder()
                .name(name)
                .build();
        Long sectionId = sectionRepository.save(section).getId();

        Section result = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new IllegalArgumentException());

        assertThat(result.getName()).isEqualTo(name);
    }
}
