package com.hlt.usermanagement.model;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "results")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultModel extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private ExamModel exam;

    @ManyToOne(optional = false)
    private UserModel student;

    @Column(nullable = false)
    private Double marksObtained;

    @Column(nullable = false)
    private Double maxMarks;

    @Column
    private String grade;

    @OneToOne(mappedBy = "result", cascade = CascadeType.ALL, orphanRemoval = true)
    private ReportCardModel reportCard;
}
