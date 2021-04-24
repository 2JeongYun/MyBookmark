package com.github.neukrang.mybookmark.domain.user;

import com.github.neukrang.mybookmark.domain.BaseTimeEntity;
import com.github.neukrang.mybookmark.domain.section.Section;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<Section> sections = new ArrayList<>();

    @Builder
    public User(String name) {
        this.name = name;
    }

    public void update(String name) {
        this.name = name;
    }
}
