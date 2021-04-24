package com.github.neukrang.mybookmark.domain.category;

import com.github.neukrang.mybookmark.domain.BaseTimeEntity;
import com.github.neukrang.mybookmark.domain.bookmark.Bookmark;
import com.github.neukrang.mybookmark.domain.section.Section;
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

    @Column(nullable = false, unique = true)
    private String name;

    private String color;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @ManyToOne
    private Section section;

    private int openCount;

    @Builder
    public Category(String name, String color, Section section) {
        this.name = name;
        this.color = color;
        this.section = setSection(section);
    }

    public void addBookmark(Bookmark bookmark) {
        bookmarks.add(bookmark);
    }

    public void deleteBookmark(Bookmark bookmark) {
        bookmarks.remove(bookmark);
    }

    private Section setSection(Section section) {
        Section prevSection = this.section;
        if (prevSection != null) {
            prevSection.deleteCategory(this);
        }

        this.section = section;
        section.addCategory(this);

        return section;
    }

    public Long update(Section section, String name, String color) {
        setSection(section);
        this.name = name;
        this.color = color;
        return this.id;
    }

    public int addOpenCount() {
        this.openCount++;
        return this.openCount;
    }
}
