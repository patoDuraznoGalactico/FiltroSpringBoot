package com.riwi.filtro_spring_boot.domain.repositories;

import com.riwi.filtro_spring_boot.domain.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
}
