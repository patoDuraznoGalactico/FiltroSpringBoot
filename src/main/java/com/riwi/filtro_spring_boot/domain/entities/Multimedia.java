package com.riwi.filtro_spring_boot.domain.entities;

import com.riwi.filtro_spring_boot.utils.enums.EnumType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "multimedia")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Multimedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated
    private EnumType type;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String url;
    @Column(nullable = false)
    private LocalDateTime created_at;
    @Column(nullable = false)
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id" , referencedColumnName = "id")
    private Lesson lesson;

}
