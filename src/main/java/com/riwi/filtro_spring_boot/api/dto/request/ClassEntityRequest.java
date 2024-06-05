package com.riwi.filtro_spring_boot.api.dto.request;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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
public class ClassEntityRequest {
    @NotBlank(message = "El nombre es requerido")
    @Size(
            min = 1,
            max = 100,
            message = "El nombre debe tener entre 1 y 100 caracteres"
    )
    private String name;
    @NotBlank(message = "La descripcion es requerida")
    @Size(
            min = 1,
            max = 3000,
            message = "La descripcion debe tener entre 1 y 3000 caracteres"
    )
    private String description;
}
