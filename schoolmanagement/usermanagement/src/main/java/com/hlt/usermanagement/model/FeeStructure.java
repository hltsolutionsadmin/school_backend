package com.hlt.usermanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Holds the total fee and breakup for an academic year, linked to a specific student.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentModel student; // Correct mapping to Student entity

    private String academicYear; // e.g., "2022-2023"
    private BigDecimal totalFees;

    @OneToMany(mappedBy = "feeStructure", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FeeBreakupItem> breakupItems;

    @OneToMany(mappedBy = "feeStructure", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Payment> payments;
}

