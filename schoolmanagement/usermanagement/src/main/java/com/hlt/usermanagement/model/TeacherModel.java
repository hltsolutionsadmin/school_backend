package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TEACHER")
@Getter
@Setter
public class TeacherModel extends GenericModel {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID", nullable = false, unique = true)
    private UserModel user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOL_ID", nullable = false)
    private SchoolModel school;

    @ManyToMany(mappedBy = "teachers")
    private Set<SubjectModel> subjects = new HashSet<>();

    @OneToOne(mappedBy = "classTeacher")
    private ClassModel classInCharge;

    @Column(name = "PRIMARY_CONTACT", length = 20)
    private String primaryContact;

    @Column(name = "QUALIFICATION", length = 100)
    private String qualification;
}