package com.riwi.filtro_spring_boot.api.dto.response;

import com.riwi.filtro_spring_boot.domain.entities.Lesson;
import com.riwi.filtro_spring_boot.utils.enums.EnumType;
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
public class MultimediaResponse {

    private Long id;
    private EnumType type;
    private String url;
    private LocalDateTime created_at;
    private Boolean active;

    private LessonBasicResponse lesson;
}
