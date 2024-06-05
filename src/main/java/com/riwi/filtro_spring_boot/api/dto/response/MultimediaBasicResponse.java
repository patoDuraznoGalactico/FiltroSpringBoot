package com.riwi.filtro_spring_boot.api.dto.response;

import com.riwi.filtro_spring_boot.utils.enums.EnumType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MultimediaBasicResponse {
    private Long id;
    private EnumType type;
    private String url;
    private LocalDateTime created_at;
    private Boolean active;
}
