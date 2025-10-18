package com.hlt.usermanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SubjectDTO {

    private Long id;

    @NotBlank(message = "Subject name is required")
    @Size(max = 100, message = "Subject name must be at most 100 characters")
    private String name;

    @Size(max = 20, message = "Subject code must be at most 20 characters")
    private String code;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    @Min(value = 0, message = "Credits must be a non-negative number")
    @Max(value = 20, message = "Credits must be realistic and not exceed 20")
    private Integer credits;

    @NotNull(message = "Active status is required")
    private Boolean active;

    @NotNull(message = "B2B Unit ID is required")
    private Long b2bUnitId;
}
