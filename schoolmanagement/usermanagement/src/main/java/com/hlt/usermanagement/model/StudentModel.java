package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "STUDENT")
@Getter
@Setter
public class StudentModel extends GenericModel {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false, unique = true)
    private UserModel user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOL_ID", nullable = false)
    private SchoolModel school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLASS_ID")
    private ClassModel classModel;

    @Column(name = "ROLL_NUMBER", nullable = false, unique = true, length = 50)
    private String rollNumber;

    @Column(name = "ADMISSION_DATE", nullable = false)
    private LocalDate admissionDate;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active = Boolean.TRUE;
}