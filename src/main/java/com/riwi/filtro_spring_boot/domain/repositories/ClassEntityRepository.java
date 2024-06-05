package com.riwi.filtro_spring_boot.domain.repositories;

import com.riwi.filtro_spring_boot.domain.entities.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassEntityRepository extends JpaRepository<ClassEntity,Long> {
}
