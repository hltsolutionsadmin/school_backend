package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TEACHER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherModel extends GenericModel {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false, unique = true)
    private UserModel user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SCHOOL_ID", nullable = false)
    private SchoolModel school;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "TEACHER_SUBJECT",
            joinColumns = @JoinColumn(name = "TEACHER_ID"),
            inverseJoinColumns = @JoinColumn(name = "SUBJECT_ID")
    )
    private Set<SubjectModel> subjects = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "CLASS_TEACHER",
            joinColumns = @JoinColumn(name = "TEACHER_ID"),
            inverseJoinColumns = @JoinColumn(name = "CLASS_ID")
    )
    private Set<ClassModel> classes = new HashSet<>();

    @OneToOne(mappedBy = "classTeacher", fetch = FetchType.LAZY)
    private ClassModel classInCharge;

    @Column(name = "PRIMARY_CONTACT", length = 20, nullable = false)
    private String primaryContact;

    @Column(name = "QUALIFICATION", length = 100)
    private String qualification;
}
