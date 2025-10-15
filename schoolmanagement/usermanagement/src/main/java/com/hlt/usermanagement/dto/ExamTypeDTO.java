package com.hlt.usermanagement.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamTypeDTO {

    /** Unique identifier for the exam type */
    private Long id;

    /** Name of the exam type (e.g., Midterm, Final) */
    private String typeName;

    /** Optional description of the exam type */
    private String description;
}
