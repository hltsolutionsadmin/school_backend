package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.AcademicResourceAssignmentDTO;
import com.hlt.usermanagement.services.AcademicResourceAssignmentService;
import com.schoolmanagement.commonservice.dto.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AcademicResourceAssignmentController {

    private final AcademicResourceAssignmentService assignmentService;

    /** Assign a resource to a subject */
    @PostMapping
    public StandardResponse<AcademicResourceAssignmentDTO> assign(@RequestBody AcademicResourceAssignmentDTO dto) {
        return StandardResponse.single(
                "Assignment created successfully",
                assignmentService.assignResource(dto)
        );
    }

    /** List assignments by resource */
    @GetMapping("/by-resource/{resourceId}")
    public StandardResponse<?> listByResource(@PathVariable Long resourceId, Pageable pageable) {
        return StandardResponse.page(
                "Assignments fetched successfully",
                assignmentService.getAssignmentsByResource(resourceId, pageable)
        );
    }

    /** List assignments by subject */
    @GetMapping("/by-subject/{subjectId}")
    public StandardResponse<?> listBySubject(@PathVariable Long subjectId, Pageable pageable) {
        return StandardResponse.page(
                "Assignments fetched successfully",
                assignmentService.getAssignmentsBySubject(subjectId, pageable)
        );
    }

    /** Delete an assignment */
    @DeleteMapping("/{id}")
    public StandardResponse<?> delete(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return StandardResponse.message("Assignment deleted successfully");
    }

    /** Set availability for an assignment */
    @PutMapping("/{id}/availability")
    public StandardResponse<AcademicResourceAssignmentDTO> setAvailability(
            @PathVariable Long id,
            @RequestBody AcademicResourceAssignmentDTO dto
    ) {
        return StandardResponse.single(
                "Availability updated successfully",
                assignmentService.setAvailability(id, dto)
        );
    }
}
