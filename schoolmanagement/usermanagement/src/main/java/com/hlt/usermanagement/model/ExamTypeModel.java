package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity representing the type of an exam.
 * Examples: Midterm, Final, Unit Test, etc.
 */
@Entity
@Table(name = "exam_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamTypeModel {

    /** Unique identifier for the exam type */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Name of the exam type (e.g., Midterm, Final) */
    @Column(nullable = false, unique = true)
    private String typeName;

    /** Optional description of the exam type */
    private String description;
}
