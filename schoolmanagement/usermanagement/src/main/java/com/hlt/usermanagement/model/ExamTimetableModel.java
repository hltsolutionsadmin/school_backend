package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "exam_timetables")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamTimetableModel extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private ExamModel exam;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private String subject;

    @Column
    private String venue;
}
