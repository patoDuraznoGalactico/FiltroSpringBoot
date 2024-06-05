package com.riwi.filtro_spring_boot.api.dto.response;

import com.riwi.filtro_spring_boot.domain.entities.ClassEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime created_at;
    private Boolean active;
    private ClassEntityBasicResponse classEntity;
}
