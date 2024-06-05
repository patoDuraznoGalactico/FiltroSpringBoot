package com.riwi.filtro_spring_boot.api.dto.request;

import com.riwi.filtro_spring_boot.domain.entities.Lesson;
import com.riwi.filtro_spring_boot.utils.enums.EnumType;
import jakarta.persistence.*;
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
public class MultimediaRequest {
    @NotNull(message = "El tipo es requerido")
    private EnumType type;
    @NotBlank(message = "La url es requerida")
    @Size(
            min = 1,
            max = 3000,
            message = "La url debe tener entre 1 y 3000 caracteres"
    )
    private String url;
}
