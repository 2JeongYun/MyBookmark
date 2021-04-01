package com.github.neukrang.mybookmark.domain.bookmark;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
public class BookMark {

    final static String DEFAULT_COLOR = "WHITE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    private String alias;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String color;

    private int openCount = 0;

    @Builder
    public BookMark(String address, String alias, String description, String color) {
        this.address = address;
        this.alias = alias;
        this.description = description;
        this.color = Objects.requireNonNullElse(color, DEFAULT_COLOR);
    }
}
