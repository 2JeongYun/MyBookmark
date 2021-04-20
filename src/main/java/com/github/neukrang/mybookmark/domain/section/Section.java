package com.github.neukrang.mybookmark.domain.section;

import com.github.neukrang.mybookmark.domain.BaseTimeEntity;
import com.github.neukrang.mybookmark.domain.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Section extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "section")
    private List<Category> categories = new ArrayList<>();

    @Builder
    public Section(String name) {
        this.name = name;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void deleteCategory(Category category) {
        categories.remove(category);
    }
}
