package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entity representing an Exam with its schedule.
 * Combines exam details, schedule, class, subject, and exam type.
 */
@Entity
@Table(name = "exams")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String examName;

    @Column(nullable = false)
    private Integer maxMarks;

    @Column(nullable = false)
    private Integer minMarks;

    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_type_id", nullable = false)
    private ExamTypeModel examType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    private ClassModel classModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private SubjectModel subjectModel;

    @Column(nullable = false)
    private LocalDateTime examDateTime;
}
