package com.hlt.usermanagement.model;

import com.hlt.usermanagement.dto.enums.AcademicRating;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Entity
@Table(name = "ACADEMIC_DETAILS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AcademicDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "OVERALL_RATING", nullable = false)
    private AcademicRating overallRating;

    @Enumerated(EnumType.STRING)
    @Column(name = "EXAMINATIONS_RATING", nullable = false)
    private AcademicRating examinationsRating;

    @Enumerated(EnumType.STRING)
    @Column(name = "DETAIL3_RATING")
    private AcademicRating detail3Rating;

    @Enumerated(EnumType.STRING)
    @Column(name = "DETAIL4_RATING")
    private AcademicRating detail4Rating;

    @Enumerated(EnumType.STRING)
    @Column(name = "DETAIL5_RATING")
    private AcademicRating detail5Rating;
}