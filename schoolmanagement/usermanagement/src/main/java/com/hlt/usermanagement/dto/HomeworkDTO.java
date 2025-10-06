package com.hlt.usermanagement.dto;

import com.hlt.usermanagement.dto.enums.HomeworkStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HomeworkDTO {

    private Long id;

    private Long studentId;

    private Long staffId;

    private Long schoolId;

    @NotBlank(message = "Subject is required")
    @Size(max = 100)
    private String subject;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Assign date is required")
    private LocalDate assignDate;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

    private HomeworkStatus status;

    private String remarks;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
