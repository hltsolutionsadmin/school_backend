package com.hlt.usermanagement.model;

import com.hlt.usermanagement.dto.enums.FeeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
        name = "fees",
        indexes = {
                @Index(name = "idx_academic_id", columnList = "ACADEMIC_ID"),
                @Index(name = "idx_student_id", columnList = "STUDENT_ID"),
                @Index(name = "idx_status", columnList = "STATUS")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeModel extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACADEMIC_ID", nullable = false)
    private AcademicModel academic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private UserModel student;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "DUE_DATE", nullable = false)
    private LocalDate dueDate;

    @Column(name = "PAID_DATE")
    private LocalDate paidDate;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private FeeStatus status;

    @Column(name = "NOTES", length = 500)
    private String notes;

    @Column(name = "INSTALLMENT_NO")
    private Integer installmentNo;
}
