package com.hlt.usermanagement.dto;

import com.hlt.usermanagement.dto.enums.AcademicRating;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;
    private Long userId;
    private String userName;
    private String rollNumber;
    private String section;

    private Long schoolId;
    private String schoolName;
    private Long classId;
    private String className;

    private LocalDate admissionDate;
    private Boolean active;

    private PersonalDetailsDTO personalDetails;
    private AcademicDetailsDTO academicDetails;


}