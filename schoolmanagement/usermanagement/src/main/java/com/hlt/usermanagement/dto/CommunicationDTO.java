package com.hlt.usermanagement.dto;

import com.hlt.usermanagement.dto.enums.CommunicationStatus;
import com.hlt.usermanagement.dto.enums.CommunicationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunicationDTO {

    private Long id;

    @NotNull(message = "CreatedBy user ID is required")
    private Long createdById;

    @NotNull(message = "Communication type is required")
    private CommunicationType type;

    @NotBlank(message = "Title is required")
    private String title;

    private String content;

    @NotNull(message = "Status is required")
    private CommunicationStatus status;
}
