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

    /** Generic resource (e.g., teacher, staff, tutor) */
    @NotNull(message = "Resource ID is required")
    private Long resourceId;

    /** Subject to which the resource is assigned */
    @NotNull(message = "Subject ID is required")
    private Long subjectId;

    /** Academic context (class/grade/section) */
    @NotNull(message = "Academic ID is required")
    private Long academicId;

    /** Status flag */
    @NotNull(message = "Active status is required")
    private Boolean active;

    /** Optional availability slots (for flexible scheduling) */
    private List<ResourceAvailabilityDTO> availabilitySlots;

    /** Nested DTO for availability time slots */
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
