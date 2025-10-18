package com.hlt.usermanagement.dto;

import com.hlt.usermanagement.dto.enums.CommunicationStatus;
import com.hlt.usermanagement.dto.enums.CommunicationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCommunicationDTO {

    private Long id;

    @NotNull(message = "CreatedById cannot be null")
    private Long createdById;

    @NotNull(message = "Communication type cannot be null")
    private CommunicationType type;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @NotBlank(message = "Content cannot be blank")
    @Size(max = 2000, message = "Content cannot exceed 2000 characters")
    private String content;

    @NotNull(message = "Status cannot be null")
    private CommunicationStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
