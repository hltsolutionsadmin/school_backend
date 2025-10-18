package com.hlt.usermanagement.dto;
import com.hlt.usermanagement.model.SubjectModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamTimetableDTO {

    private Long id;

    @NotNull(message = "Exam ID is required")
    private Long examId;

    @NotBlank(message = "Subject is required")
    private SubjectModel subject;

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    private LocalDateTime endTime;

    private String venue;
}
