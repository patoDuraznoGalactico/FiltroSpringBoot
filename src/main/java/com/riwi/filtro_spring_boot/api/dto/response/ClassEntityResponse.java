package com.riwi.filtro_spring_boot.api.dto.response;

import com.riwi.filtro_spring_boot.domain.entities.Lesson;
import com.riwi.filtro_spring_boot.domain.entities.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassEntityResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime created_at;
    private Boolean active;
    private List<StudentBasicResponse> students;
    private List<LessonBasicResponse> lessons;
}
