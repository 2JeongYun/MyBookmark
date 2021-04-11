package com.github.neukrang.mybookmark.domain.Category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String color;

    private int openCount;

    @Builder
    public Category(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public int addOpenCount() {
        this.openCount++;
        return this.openCount;
    }
}
