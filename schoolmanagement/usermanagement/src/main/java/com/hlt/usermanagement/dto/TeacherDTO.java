package com.hlt.usermanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Teacher entity.
 * Keeps teacher info separate from persistence layer.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {

    private Long id;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "School ID cannot be null")
    private Long schoolId;

    @NotBlank(message = "Primary contact cannot be blank")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Primary contact must be a valid phone number")
    private String primaryContact;

    @Size(max = 100, message = "Qualification must not exceed 100 characters")
    private String qualification;
}
