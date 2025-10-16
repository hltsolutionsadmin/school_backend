package com.hlt.usermanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademicEventDTO {

    private Long id;

    @NotNull(message = "Academic Unit ID is required")
    private Long academicUnitId;

    @NotNull(message = "Event type is required")
    private AcademicEventType type;

    @NotBlank(message = "Event name is required")
    private String name;

    @NotNull(message = "Event date is required")
    private LocalDate date;

    private String details;
}
