package com.hlt.usermanagement.dto;

import com.hlt.usermanagement.dto.enums.AcademicRating;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademicDetailsDTO {

    private AcademicRating overallRating;
    private AcademicRating examinationsRating;
    private AcademicRating detail3Rating;
    private AcademicRating detail4Rating;
    private AcademicRating detail5Rating;
}