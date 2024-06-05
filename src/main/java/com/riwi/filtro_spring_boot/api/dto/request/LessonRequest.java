package com.riwi.filtro_spring_boot.api.dto.request;

import com.riwi.filtro_spring_boot.domain.entities.ClassEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonRequest {
    @NotBlank(message = "El titulo es requerido")
    @Size(
            min = 1,
            max = 100,
            message = "El titulo debe tener entre 1 y 100 caracteres"
    )
    private String title;
    @NotBlank(message = "El contenido es requerido")
    @Size(
            min = 1,
            max = 3000,
            message = "El contenido debe tener entre 1 y 3000 caracteres"
    )
    private String content;

    @NotNull(message = "El id de la clase es obligatorio")
    @Min(value = 1, message = "El id de la clase debe ser mayor a cero")
    private Long classEntity;
}
