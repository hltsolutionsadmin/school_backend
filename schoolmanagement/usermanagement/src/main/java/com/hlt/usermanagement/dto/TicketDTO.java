package com.hlt.usermanagement.dto;

import com.hlt.usermanagement.dto.enums.TicketStatus;
import com.hlt.usermanagement.dto.enums.TicketType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {

    private Long id;

    @NotNull(message = "CreatedBy user ID is required")
    private Long createdById;

    @NotNull(message = "Ticket type is required")
    private TicketType type;

    @NotBlank(message = "Title is required")
    private String title;

    private String content;

    @NotNull(message = "Ticket status is required")
    private TicketStatus status;
}
