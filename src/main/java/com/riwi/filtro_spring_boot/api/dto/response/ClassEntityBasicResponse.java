package com.riwi.filtro_spring_boot.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassEntityBasicResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime created_at;
    private Boolean active;
}