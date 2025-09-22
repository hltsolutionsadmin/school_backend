package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "ATTENDANCE")
@Getter
@Setter
public class AttendanceModel extends GenericModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private StudentModel student;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @Column(name = "PRESENT", nullable = false)
    private Boolean present;

}
