package com.hlt.usermanagement.model;

import com.hlt.usermanagement.dto.AcademicEventType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "academic_events")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademicEventModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "academic_id")
    private AcademicModel academicUnit;

    @Enumerated(EnumType.STRING)
    private AcademicEventType type;

    private String name;

    private LocalDate date;

    @Column(length = 2000)
    private String details;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}