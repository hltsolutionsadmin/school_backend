package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.AcademicEventDTO;
import com.hlt.usermanagement.services.AcademicEventService;
import com.schoolmanagement.commonservice.dto.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/academic-events")
@RequiredArgsConstructor
public class AcademicEventController {

    private final AcademicEventService academicEventService;

    @PostMapping
    public StandardResponse<AcademicEventDTO> createEvent(@Valid @RequestBody AcademicEventDTO dto) {
        AcademicEventDTO created = academicEventService.createEvent(dto);
        return StandardResponse.single("Academic event created successfully", created);
    }

    @PutMapping("/{id}")
    public StandardResponse<AcademicEventDTO> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody AcademicEventDTO dto) {
        AcademicEventDTO updated = academicEventService.updateEvent(id, dto);
        return StandardResponse.single("Academic event updated successfully", updated);
    }

    @GetMapping("/{id}")
    public StandardResponse<AcademicEventDTO> getEventById(@PathVariable Long id) {
        AcademicEventDTO event = academicEventService.getEventById(id);
        return StandardResponse.single("Academic event fetched successfully", event);
    }

    @GetMapping
    public StandardResponse<Page<AcademicEventDTO>> getAllEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<AcademicEventDTO> events = academicEventService.getAllEvents(page, size);
        return StandardResponse.page("Academic events fetched successfully", events);
    }

    @DeleteMapping("/{id}")
    public StandardResponse<Void> deleteEvent(@PathVariable Long id) {
        academicEventService.deleteEvent(id);
        return StandardResponse.message("Academic event deleted successfully");
    }
}
