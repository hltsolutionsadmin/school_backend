package com.hlt.usermanagement.dto;

import com.hlt.usermanagement.dto.enums.TaskType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {

    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Task type is required")
    private TaskType taskType;

    @NotNull(message = "Academic ID is required")
    private Long academicId;

    private Long initiatedById;

    @FutureOrPresent(message = "Due date must not be in the past")
    private LocalDate dueDate;

    private Boolean active = true;

    private String attachmentUrl;

    private LocalDate taskDate;

    private Integer priority;
}
