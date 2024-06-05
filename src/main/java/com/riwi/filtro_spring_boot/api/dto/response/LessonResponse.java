package com.riwi.filtro_spring_boot.api.dto.response;

import com.riwi.filtro_spring_boot.domain.entities.ClassEntity;
import com.riwi.filtro_spring_boot.domain.entities.Multimedia;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime created_at;
    private Boolean active;
    private ClassEntityBasicResponse classEntity;
    private List<MultimediaBasicResponse> multimedia;
}
