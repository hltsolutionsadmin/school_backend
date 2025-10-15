package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Table(
        name = "ACADEMIC_UNIT",
        indexes = {
                @Index(name = "idx_class_school", columnList = "B2B_UNIT_ID"),
                @Index(name = "idx_class_name_section", columnList = "CLASS_NAME, SECTION")
        }
)
@Getter
@Setter
public class AcademicUnitModel extends AuditableModel {

    @NotBlank
    @Size(max = 50)
    @Column(name = "CLASS_NAME", nullable = false)
    private String className;

    @Size(max = 10)
    @Column(name = "SECTION")
    private String section;

    @Column(name = "ACADEMIC_YEAR", nullable = false)
    private String academicYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "B2B_UNIT_ID")
    private B2BUnitModel b2bUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLASS_TEACHER_ID")
    private UserModel classTeacher;

//    @OneToMany(mappedBy = "studentClass", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<UserModel> students = new ArrayList<>();

//    @OneToMany(mappedBy = "academicUnit", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<SubjectModel> subjects = new HashSet<>();

    @Column(name = "CAPACITY")
    private Integer capacity;

    @Column(name = "ROOM_NUMBER", length = 20)
    private String roomNumber;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive = true;

    @Column(name = "TIMETABLE_JSON", columnDefinition = "TEXT")
    private String timetable;

    @Column(name = "NOTES", length = 500)
    private String notes;
}