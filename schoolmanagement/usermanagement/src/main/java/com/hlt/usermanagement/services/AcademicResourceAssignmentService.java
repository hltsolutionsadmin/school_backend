package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.AcademicResourceAssignmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AcademicResourceAssignmentService {

    AcademicResourceAssignmentDTO assignResource(AcademicResourceAssignmentDTO dto);

    Page<AcademicResourceAssignmentDTO> getAssignmentsByResource(Long resourceId, Pageable pageable);

    Page<AcademicResourceAssignmentDTO> getAssignmentsBySubject(Long subjectId, Pageable pageable);

    void deleteAssignment(Long id);

    AcademicResourceAssignmentDTO setAvailability(Long assignmentId, AcademicResourceAssignmentDTO availabilityDto);
}
