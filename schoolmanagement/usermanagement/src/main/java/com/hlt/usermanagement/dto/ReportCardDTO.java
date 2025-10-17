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
public class ReportCardDTO {

    private Long id;

    @NotNull(message = "Result ID is required")
    private Long resultId;

    @NotNull(message = "Academic ID is required")
    private Long academicId;

    @NotBlank(message = "Student name is required")
    private String studentName;

    @NotBlank(message = "Remarks are required")
    private String remarks;

    @NotNull(message = "Percentage is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Percentage cannot be negative")
    @DecimalMax(value = "100.0", inclusive = true, message = "Percentage cannot exceed 100")
    private Double percentage;

    private String grade;
}
