package com.hlt.usermanagement.model;

import com.hlt.usermanagement.dto.enums.EducationLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "EDUCATION")
@Getter
@Setter
public class EducationModel extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "EDUCATION_LEVEL", nullable = false, length = 30)
	private EducationLevel educationLevel;

	@Column(name = "INSTITUTION_NAME", nullable = false)
	private String institutionName;

	@Column(name = "START_DATE", nullable = false)
	private LocalDate startDate;

	@Column(name = "END_DATE")
	private LocalDate endDate;

	@Column(name = "GRADE", length = 10)
	private String grade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STUDENT_ID", nullable = false)
	private UserModel student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SCHOOL_ID", nullable = false)
	private B2BUnitModel school;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLASS_ID")
	private AcademicUnitModel classModel;
}
