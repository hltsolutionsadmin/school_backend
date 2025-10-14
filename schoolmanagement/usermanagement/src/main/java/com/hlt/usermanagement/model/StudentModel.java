package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor; // Added for JPA compatibility
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "STUDENT")
@Getter
@Setter
@NoArgsConstructor // Added for JPA compatibility
@ToString(exclude = {"user", "school", "classModel", "personalDetails", "academicDetails", "grades", "attendanceRecords"})
public class StudentModel extends GenericModel { // Assumes GenericModel exists

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false, unique = true, foreignKey = @ForeignKey(name = "FK_STUDENT_USER"))
    private UserModel user; // Assumes UserModel exists

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOL_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_STUDENT_SCHOOL"))
    private SchoolModel school; // Assumes SchoolModel exists

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLASS_ID", foreignKey = @ForeignKey(name = "FK_STUDENT_CLASS"))
    private ClassModel classModel; // Assumes ClassModel exists

    @Column(name = "ROLL_NUMBER", nullable = false, unique = true, length = 50)
    private String rollNumber;

    @Column(name = "ADMISSION_DATE", nullable = false)
    private LocalDate admissionDate;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active = Boolean.TRUE;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSONAL_DETAILS_ID", foreignKey = @ForeignKey(name = "FK_STUDENT_PERSONAL_DETAILS"))
    private PersonalDetails personalDetails;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "ACADEMIC_DETAILS_ID", foreignKey = @ForeignKey(name = "FK_STUDENT_ACADEMIC_DETAILS"))
    private AcademicDetails academicDetails;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GradeModel> grades = new ArrayList<>(); // Assumes GradeModel exists

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttendanceModel> attendanceRecords = new ArrayList<>(); // Assumes AttendanceModel exists

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FeeStructure> feeStructures;

}
