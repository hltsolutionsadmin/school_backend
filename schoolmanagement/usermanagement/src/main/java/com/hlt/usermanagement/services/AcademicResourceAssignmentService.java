package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.AcademicResourceAssignmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AcademicResourceAssignmentService {

    /** Assign a resource (teacher/staff/tutor) to a subject within an academic unit */
    AcademicResourceAssignmentDTO assignResource(AcademicResourceAssignmentDTO dto);

    /** Get all assignments for a specific resource */
    Page<AcademicResourceAssignmentDTO> getAssignmentsByResource(Long resourceId, Pageable pageable);

    /** Get all assignments for a specific subject */
    Page<AcademicResourceAssignmentDTO> getAssignmentsBySubject(Long subjectId, Pageable pageable);

    /** Delete an assignment by its ID */
    void deleteAssignment(Long id);

    /** Set or update availability slots for a given assignment */
    AcademicResourceAssignmentDTO setAvailability(Long assignmentId, AcademicResourceAssignmentDTO availabilityDto);
}
