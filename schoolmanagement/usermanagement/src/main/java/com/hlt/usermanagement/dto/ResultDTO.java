package com.hlt.usermanagement.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDTO {

    private Long id;

    @NotNull(message = "Exam ID is required")
    private Long examId;

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Marks obtained are required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Marks cannot be negative")
    private Double marksObtained;

    @NotNull(message = "Maximum marks are required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Max marks cannot be negative")
    private Double maxMarks;

    private String grade;

    private ReportCardDTO reportCard;
}
