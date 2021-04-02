package com.github.neukrang.mybookmark.domain.bookmark;

import com.github.neukrang.mybookmark.domain.Category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class BookMark {

    final static String DEFAULT_COLOR = "WHITE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private String address;

    private String alias;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String color;

    private int openCount;

    @Builder
    public BookMark(String address, String alias, String description, String color) {
        this.address = address;
        this.alias = alias;
        this.description = description;
        this.color = color;
    }

    public void update(String alias, String description, String color) {
        this.alias = alias;
        this.description = description;
        this.color = color;
    }
}
