package com.hlt.usermanagement.dto;

import lombok.*;
import java.time.LocalDateTime;

/**
 * DTO for transferring Exam data
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamDto {
    private Long id;
    private String examName;
    private Integer maxMarks;
    private Integer minMarks;
    private String remarks;

    private Long examTypeId;
    private String examTypeName;

    private Long classId;
    private String className;
    private String section;

    private Long subjectId;
    private String subjectName;

    private LocalDateTime examDateTime;
}
