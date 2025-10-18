package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.AcademicResourceAssignmentDTO;
import com.hlt.usermanagement.model.AcademicModel;
import com.hlt.usermanagement.model.SubjectModel;
import com.hlt.usermanagement.model.AcademicResourceAssignmentModel;
import com.hlt.usermanagement.model.UserModel;
import com.hlt.usermanagement.model.ResourceAvailability;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AcademicResourceAssignmentPopulator implements Populator<AcademicResourceAssignmentModel, AcademicResourceAssignmentDTO> {

    // Model → DTO
    @Override
    public void populate(AcademicResourceAssignmentModel source, AcademicResourceAssignmentDTO target) {
        if (source == null || target == null) return;

        target.setId(source.getId());
        target.setActive(source.getActive());
        target.setResourceId(source.getResource() != null ? source.getResource().getId() : null);
        target.setSubjectId(source.getSubject() != null ? source.getSubject().getId() : null);
        target.setAcademicId(source.getAcademic() != null ? source.getAcademic().getId() : null);

        if (source.getAvailabilitySlots() != null) {
            target.setAvailabilitySlots(
                    source.getAvailabilitySlots().stream()
                            .map(slot -> AcademicResourceAssignmentDTO.ResourceAvailabilityDTO.builder()
                                    .dayOfWeek(slot.getDayOfWeek())
                                    .startTime(slot.getStartTime())
                                    .endTime(slot.getEndTime())
                                    .build())
                            .collect(Collectors.toList())
            );
        }
    }

    public AcademicResourceAssignmentDTO toDTO(AcademicResourceAssignmentModel source) {
        if (source == null) return null;
        AcademicResourceAssignmentDTO dto = new AcademicResourceAssignmentDTO();
        populate(source, dto);
        return dto;
    }

    // DTO → Model
    public AcademicResourceAssignmentModel toModel(AcademicResourceAssignmentDTO dto) {
        if (dto == null) return null;
        AcademicResourceAssignmentModel model = new AcademicResourceAssignmentModel();

        model.setId(dto.getId());
        model.setActive(dto.getActive());

        if (dto.getResourceId() != null) {
            UserModel resource = new UserModel();
            resource.setId(dto.getResourceId());
            model.setResource(resource);
        }

        if (dto.getSubjectId() != null) {
            SubjectModel subject = new SubjectModel();
            subject.setId(dto.getSubjectId());
            model.setSubject(subject);
        }

        if (dto.getAcademicId() != null) {
            AcademicModel academic = new AcademicModel();
            academic.setId(dto.getAcademicId());
            model.setAcademic(academic);
        }

        if (dto.getAvailabilitySlots() != null) {
            model.setAvailabilitySlots(
                    dto.getAvailabilitySlots().stream()
                            .map(slot -> ResourceAvailability.builder()
                                    .dayOfWeek(slot.getDayOfWeek())
                                    .startTime(slot.getStartTime())
                                    .endTime(slot.getEndTime())
                                    .build())
                            .collect(Collectors.toList())
            );
        }

        return model;
    }
}
