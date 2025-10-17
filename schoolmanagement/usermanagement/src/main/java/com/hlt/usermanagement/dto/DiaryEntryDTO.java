package com.hlt.usermanagement.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiaryEntryDTO {

    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title can have at most 255 characters")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Academic ID is required")
    private Long academicId;

    @NotNull(message = "Initiated by user ID is required")
    private Long initiatedById;

    private Boolean active = true;

    @Size(max = 500, message = "Attachment URL can have at most 500 characters")
    private String attachmentUrl;

    @Min(value = 1, message = "Priority must be at least 1")
    @Max(value = 5, message = "Priority can be at most 5")
    private Integer priority;
}
