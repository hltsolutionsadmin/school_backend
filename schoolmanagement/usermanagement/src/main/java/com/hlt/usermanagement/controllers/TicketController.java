package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.TicketDTO;
import com.hlt.usermanagement.services.TicketService;
import com.schoolmanagement.commonservice.dto.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public StandardResponse<TicketDTO> create(@Valid @RequestBody TicketDTO dto) {
        TicketDTO created = ticketService.create(dto);
        return StandardResponse.single("Ticket created successfully", created);
    }

    @PutMapping("/{id}")
    public StandardResponse<TicketDTO> update(@PathVariable Long id, @Valid @RequestBody TicketDTO dto) {
        TicketDTO updated = ticketService.update(id, dto);
        return StandardResponse.single("Ticket updated successfully", updated);
    }

    @GetMapping("/{id}")
    public StandardResponse<TicketDTO> getById(@PathVariable Long id) {
        TicketDTO dto = ticketService.getById(id);
        return StandardResponse.single("Ticket retrieved successfully", dto);
    }

    @GetMapping
    public StandardResponse<Page<TicketDTO>> search(
            @RequestParam(required = false) Long createdById,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long academicId,
            @RequestParam(required = false) Long b2bUnitId,
            Pageable pageable) {

        Page<TicketDTO> page = ticketService.search(createdById, type, status, academicId, b2bUnitId, pageable);
        return StandardResponse.page("Tickets fetched successfully", page);
    }

    @GetMapping("/by-academic/{academicId}")
    public StandardResponse<java.util.List<TicketDTO>> getByAcademic(
            @PathVariable Long academicId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String type) {
        return StandardResponse.list(
                "Tickets fetched successfully",
                ticketService.getTicketsByAcademic(academicId, status, type)
        );
    }

    @DeleteMapping("/{id}")
    public StandardResponse<Void> delete(@PathVariable Long id) {
        ticketService.delete(id);
        return StandardResponse.message("Ticket deleted successfully");
    }
}
