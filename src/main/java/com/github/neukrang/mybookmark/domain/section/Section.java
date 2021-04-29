package com.github.neukrang.mybookmark.domain.section;

import com.github.neukrang.mybookmark.domain.BaseTimeEntity;
import com.github.neukrang.mybookmark.domain.category.Category;
import com.github.neukrang.mybookmark.domain.user.User;
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

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "section", cascade = CascadeType.REMOVE)
    private List<Category> categories = new ArrayList<>();

    @ManyToOne
    private User user;

    @Builder
    public Section(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public Section update(String name) {
        this.name = name;
        return this;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void deleteCategory(Category category) {
        categories.remove(category);
    }
}
