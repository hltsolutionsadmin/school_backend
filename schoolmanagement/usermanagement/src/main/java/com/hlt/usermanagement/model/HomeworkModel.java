package com.hlt.usermanagement.model;

import com.hlt.usermanagement.dto.enums.HomeworkStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(
        name = "HOMEWORK",
        indexes = {
                @Index(name = "idx_homework_student", columnList = "STUDENT_ID"),
                @Index(name = "idx_homework_staff", columnList = "STAFF_ID"),
                @Index(name = "idx_homework_school", columnList = "SCHOOL_ID")
        }
)
@Getter
@Setter
public class HomeworkModel extends AuditableModel {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private UserModel student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STAFF_ID", nullable = false)
    private StaffModel staff;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SCHOOL_ID", nullable = false)
    private B2BUnitModel school;

    @NotBlank
    @Size(max = 100)
    @Column(name = "SUBJECT", nullable = false, length = 100)
    private String subject;

    @NotBlank
    @Column(name = "DESCRIPTION", nullable = false, columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Column(name = "ASSIGN_DATE", nullable = false)
    private LocalDate assignDate;

    @NotNull
    @Column(name = "DUE_DATE", nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false, length = 50)
    private HomeworkStatus status = HomeworkStatus.ASSIGNED;

    @Column(name = "REMARKS", length = 255)
    private String remarks;
}
