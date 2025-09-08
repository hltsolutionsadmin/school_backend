package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "CLASS",
        indexes = {
                @Index(name = "idx_class_school", columnList = "SCHOOL_ID"),
                @Index(name = "idx_class_name_section", columnList = "CLASS_NAME, SECTION")
        }
)
@Getter
@Setter
public class ClassModel extends AuditableModel {

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
    @JoinColumn(name = "SCHOOL_ID", nullable = false)
    private SchoolModel school;

    /** Class Teacher */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLASS_TEACHER_ID")
    private TeacherModel classTeacher;

    /** Students in this class */
    @OneToMany(mappedBy = "classModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentModel> students = new ArrayList<>();

    /** Subjects offered in this class */
    @OneToMany(mappedBy = "classModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SubjectModel> subjects = new HashSet<>();
}
