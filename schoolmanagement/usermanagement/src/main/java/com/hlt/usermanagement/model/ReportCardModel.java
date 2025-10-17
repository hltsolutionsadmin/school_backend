package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "report_cards")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportCardModel extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private ResultModel result;

    @ManyToOne(optional = false)
    private AcademicModel academic;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private String remarks;

    @Column(nullable = false)
    private Double percentage;

    @Column(nullable = false)
    private String grade;
}
