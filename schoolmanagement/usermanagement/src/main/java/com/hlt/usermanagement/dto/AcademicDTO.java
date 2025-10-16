package com.hlt.usermanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AcademicDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @Size(max = 20, message = "Code must be at most 20 characters")
    private String code;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    @NotNull(message = "Active status is required")
    private Boolean active;

    @Min(value = 0, message = "Sort order must be non-negative")
    private Integer sortOrder;

    @NotNull(message = "B2B Unit ID is required")
    private Long b2bUnitId;

    @Size(max = 10, message = "Section must be at most 10 characters")
    private String section;

    @Min(value = 0, message = "Capacity must be non-negative")
    private Integer capacity;

    private LocalDate startDate;

    private LocalDate endDate;

    @Min(value = 0, message = "Subject count must be non-negative")
    private Integer subjectCount;

    @Min(value = 0, message = "Teacher count must be non-negative")
    private Integer teacherCount;

    @Size(max = 500, message = "Notes must be at most 500 characters")
    private String notes;
}
