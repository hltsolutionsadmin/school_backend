package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SUBJECT")
@Getter
@Setter
public class SubjectModel extends GenericModel {
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CODE", nullable = false, unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLASS_ID", nullable = false)
    private AcademicModel classModel;

    @ManyToMany
    @JoinTable(
            name = "SUBJECT_TEACHERS",
            joinColumns = @JoinColumn(name = "SUBJECT_ID"),
            inverseJoinColumns = @JoinColumn(name = "TEACHER_ID")
    )
    private Set<UserModel> teachers = new HashSet<>();
}
