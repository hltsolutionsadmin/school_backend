package com.hlt.usermanagement.dto;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamDTO {

    private Long id;

    @NotBlank(message = "Exam name is required")
    private String name;

    @NotNull(message = "Academic ID is required")
    private Long academicId;

    private List<ExamTimetableDTO> timetable;

    private List<ResultDTO> results;
}
