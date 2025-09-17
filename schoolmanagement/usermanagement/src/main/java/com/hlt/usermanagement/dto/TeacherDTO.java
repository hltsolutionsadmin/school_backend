package com.hlt.usermanagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TeacherDTO {
    private Long id;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "School ID cannot be null")
    private Long schoolId;

    private String primaryContact;
    private String qualification;
}