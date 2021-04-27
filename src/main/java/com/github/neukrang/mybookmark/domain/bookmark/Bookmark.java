package com.github.neukrang.mybookmark.domain.bookmark;

import com.github.neukrang.mybookmark.domain.BaseTimeEntity;
import com.github.neukrang.mybookmark.domain.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Bookmark extends BaseTimeEntity {

    final static String DEFAULT_COLOR = "WHITE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false, unique = true)
    private String address;

    private String alias;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String color;

    private int openCount;

    @Builder
    public Bookmark(Category category, String address, String alias, String description, String color) {
        this.category = setCategory(category);
        this.address = address;
        this.alias = alias;
        this.description = description;
        this.color = color;
    }

    private Category setCategory(Category category) {
        Category prevCategory = this.category;
        if (prevCategory != null) {
            prevCategory.deleteBookmark(this);
        }

        this.category = category;
        category.addBookmark(this);

        return category;
    }

    public Bookmark update(Category category, String alias, String description, String color) {
        setCategory(category);
        this.alias = alias;
        this.description = description;
        this.color = color;
        return this;
    }

    public int addOpenCount() {
        this.openCount++;
        this.category.addOpenCount();
        return this.openCount;
    }
}
