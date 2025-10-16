package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "academics",
        indexes = {
                @Index(name = "idx_b2b_unit_id", columnList = "B2B_UNIT_ID"),
                @Index(name = "idx_name", columnList = "NAME")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicModel extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CODE", nullable = true, unique = true)
    private String code;

    @Column(name = "DESCRIPTION", nullable = true)
    private String description;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active = true;

    @Column(name = "SORT_ORDER", nullable = true)
    private Integer sortOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "B2B_UNIT_ID", nullable = false)
    private B2BUnitModel b2bUnit;

    @Column(name = "SECTION", nullable = true)
    private String section;

    @Column(name = "CAPACITY", nullable = true)
    private Integer capacity;

    @Column(name = "START_DATE", nullable = true)
    private LocalDate startDate;

    @Column(name = "END_DATE", nullable = true)
    private LocalDate endDate;

    @Column(name = "SUBJECT_COUNT", nullable = true)
    private Integer subjectCount;

    @Column(name = "TEACHER_COUNT", nullable = true)
    private Integer teacherCount;

    @Column(name = "NOTES", length = 500, nullable = true)
    private String notes;

}
