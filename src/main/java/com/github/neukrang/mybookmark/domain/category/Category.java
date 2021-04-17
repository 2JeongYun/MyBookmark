package com.github.neukrang.mybookmark.domain.category;

import com.github.neukrang.mybookmark.domain.BaseTimeEntity;
import com.github.neukrang.mybookmark.domain.bookmark.Bookmark;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Category extends BaseTimeEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String color;

    @OneToMany(mappedBy = "category")
    private List<Bookmark> bookmarks = new ArrayList<>();

    // FIXME 기본값 설정
    private int openCount;

    @Builder
    public Category(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public void addBookmark(Bookmark bookmark) {
        bookmarks.add(bookmark);
    }

    public Long update(String name, String color) {
        this.name = name;
        this.color = color;

        return this.id;
    }

    public int addOpenCount() {
        this.openCount++;
        return this.openCount;
    }
}
