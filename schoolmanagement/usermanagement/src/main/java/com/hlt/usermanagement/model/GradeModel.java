package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "GRADE")
@Getter
@Setter
public class GradeModel extends GenericModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private UserModel student;

    @Column(name = "SUBJECT", nullable = false)
    private String subject;

    @Column(name = "GRADE", nullable = false, length = 5)
    private String grade;

    @Column(name = "SCORE")
    private Double score;

    @Column(name = "REMARKS", length = 500)
    private String remarks;
}
