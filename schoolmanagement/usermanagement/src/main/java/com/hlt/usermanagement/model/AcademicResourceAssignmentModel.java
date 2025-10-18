package com.hlt.usermanagement.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "teacher_subject_mapping")
@Getter
@Setter
public class TeacherSubjectMappingModel extends GenericModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private UserModel teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private SubjectModel subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_id", nullable = false)
    private AcademicModel academic;

    @Column(name = "active", nullable = false)
    private Boolean active = true;
}
