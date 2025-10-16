package com.hlt.usermanagement.dto;

import com.hlt.usermanagement.dto.enums.GrievanceStatus;
import com.hlt.usermanagement.dto.enums.GrievanceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrievanceDTO {
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Type is required")
    private GrievanceType type;

    @NotBlank(message = "Description is required")
    private String description;

    private GrievanceStatus status;

    private String attachmentUrl;

    private boolean active = true;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

