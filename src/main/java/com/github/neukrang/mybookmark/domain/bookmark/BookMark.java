package com.github.neukrang.mybookmark.domain.bookmark;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@Entity
public class BookMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;
    private String alias;
    private String description;
    private String color;
    private int openCount = 0;

    @Builder
    public BookMark(String address, String alias, String description, String color) {
        this.address = address;
        this.alias = alias;
        this.description = description;
        this.color = color;
    }
}
