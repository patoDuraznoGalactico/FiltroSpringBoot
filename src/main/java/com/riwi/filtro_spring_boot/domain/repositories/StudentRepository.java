package com.riwi.filtro_spring_boot.domain.repositories;

import com.riwi.filtro_spring_boot.domain.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
