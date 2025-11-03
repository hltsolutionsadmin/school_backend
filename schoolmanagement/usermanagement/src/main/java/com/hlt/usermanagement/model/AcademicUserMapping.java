package com.hlt.usermanagement.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "academic_user_mapping",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"academic_id", "user_id"})},
        indexes = {@Index(name = "idx_academic_id", columnList = "academic_id")}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicUserMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "academic_id", nullable = false)
    private AcademicModel academic;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    //TODO ::Change to EROLES -enums
    @Column(name = "role", nullable = false)
    private String role;
}
