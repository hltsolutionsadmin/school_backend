package com.hlt.usermanagement.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicResourceAssignmentDTO {

    private Long id;

    @NotNull(message = "Resource ID is required")
    private Long resourceId;

    @NotNull(message = "Subject ID is required")
    private Long subjectId;

    @NotNull(message = "Academic ID is required")
    private Long academicId;

    @NotNull(message = "Active status is required")
    private Boolean active;

    private List<ResourceAvailabilityDTO> availabilitySlots;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ResourceAvailabilityDTO {

        @NotNull(message = "Day of week is required")
        private DayOfWeek dayOfWeek;

        @NotNull(message = "Start time is required")
        private LocalTime startTime;

        @NotNull(message = "End time is required")
        private LocalTime endTime;
    }
}
