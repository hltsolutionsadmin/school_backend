package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.AcademicResourceAssignmentDTO;
import com.hlt.usermanagement.model.AcademicResourceAssignmentModel;
import com.hlt.usermanagement.populator.AcademicResourceAssignmentPopulator;
import com.hlt.usermanagement.repository.AcademicResourceAssignmentRepository;
import com.hlt.usermanagement.services.AcademicResourceAssignmentService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class AcademicResourceAssignmentServiceImpl implements AcademicResourceAssignmentService {

    private final AcademicResourceAssignmentRepository academicResourceAssignmentRepository;
    private final AcademicResourceAssignmentPopulator academicResourceAssignmentPopulator;

    @Override
    public AcademicResourceAssignmentDTO assignResource(AcademicResourceAssignmentDTO dto) {
        AcademicResourceAssignmentModel model = academicResourceAssignmentPopulator.toModel(dto);
        AcademicResourceAssignmentModel saved = academicResourceAssignmentRepository.save(model);
        return academicResourceAssignmentPopulator.toDTO(saved);
    }

    @Override
    public Page<AcademicResourceAssignmentDTO> getAssignmentsByResource(Long resourceId, Pageable pageable) {
        Page<AcademicResourceAssignmentModel> page = academicResourceAssignmentRepository.findByResourceId(resourceId, pageable);
        return page.map(academicResourceAssignmentPopulator::toDTO);
    }

    @Override
    public Page<AcademicResourceAssignmentDTO> getAssignmentsBySubject(Long subjectId, Pageable pageable) {
        Page<AcademicResourceAssignmentModel> page = academicResourceAssignmentRepository.findBySubjectId(subjectId, pageable);
        return page.map(academicResourceAssignmentPopulator::toDTO);
    }

    @Override
    public void deleteAssignment(Long id) {
        AcademicResourceAssignmentModel model = academicResourceAssignmentRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.ASSIGNMENT_NOT_FOUND));
        academicResourceAssignmentRepository.delete(model);
    }

    @Override
    public AcademicResourceAssignmentDTO setAvailability(Long assignmentId, AcademicResourceAssignmentDTO availabilityDto) {
        AcademicResourceAssignmentModel model = academicResourceAssignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.ASSIGNMENT_NOT_FOUND));

        model.setAvailabilitySlots(
                availabilityDto.getAvailabilitySlots() != null
                        ? academicResourceAssignmentPopulator.toModel(availabilityDto).getAvailabilitySlots()
                        : null
        );

        AcademicResourceAssignmentModel saved = academicResourceAssignmentRepository.save(model);
        return academicResourceAssignmentPopulator.toDTO(saved);
    }


    //TODO add functionality  after
    // Check if a resource is cjurently available for a given subject  acadeemic unit
    public boolean isResourceAvailable(Long resourceId, Long subjectId, Long academicId, LocalTime checkTime) {
        AcademicResourceAssignmentModel assignment = academicResourceAssignmentRepository
                .findByResourceIdAndSubjectIdAndAcademicIdAndActiveTrue(resourceId, subjectId, academicId)
                .orElse(null);

        if (assignment == null) return false;
        if (assignment.getAvailabilitySlots() == null || assignment.getAvailabilitySlots().isEmpty()) return true;

        DayOfWeek today = LocalDate.now().getDayOfWeek();
        return assignment.getAvailabilitySlots().stream().anyMatch(slot ->
                slot.getDayOfWeek() == today &&
                        !checkTime.isBefore(slot.getStartTime()) &&
                        !checkTime.isAfter(slot.getEndTime())
        );
    }
}
