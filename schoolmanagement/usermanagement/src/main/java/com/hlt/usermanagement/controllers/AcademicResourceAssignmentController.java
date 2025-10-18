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

    @PostMapping
    public StandardResponse<AcademicResourceAssignmentDTO> assign(@RequestBody AcademicResourceAssignmentDTO dto) {
        return StandardResponse.single(
                "Assignment created successfully",
                assignmentService.assignResource(dto)
        );
    }

    @GetMapping("/by-resource/{resourceId}")
    public StandardResponse<?> listByResource(@PathVariable Long resourceId, Pageable pageable) {
        return StandardResponse.page(
                "Assignments fetched successfully",
                assignmentService.getAssignmentsByResource(resourceId, pageable)
        );
    }

    @GetMapping("/by-subject/{subjectId}")
    public StandardResponse<?> listBySubject(@PathVariable Long subjectId, Pageable pageable) {
        return StandardResponse.page(
                "Assignments fetched successfully",
                assignmentService.getAssignmentsBySubject(subjectId, pageable)
        );
    }

    @DeleteMapping("/{id}")
    public StandardResponse<?> delete(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return StandardResponse.message("Assignment deleted successfully");
    }

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
