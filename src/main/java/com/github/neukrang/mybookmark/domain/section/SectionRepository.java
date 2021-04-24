package com.github.neukrang.mybookmark.domain.section;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {

    @Query("SELECT s FROM Section s ORDER BY s.name")
    List<Section> findAllOrderByName();
}
