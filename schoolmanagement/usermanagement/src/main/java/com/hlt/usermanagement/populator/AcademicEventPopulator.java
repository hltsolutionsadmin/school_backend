package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.AcademicEventDTO;
import com.hlt.usermanagement.model.AcademicEventModel;
import com.hlt.usermanagement.model.AcademicModel;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;

@Component
public class AcademicEventPopulator implements Populator<AcademicEventModel, AcademicEventDTO> {

    public AcademicEventDTO toDTO(AcademicEventModel source) {
        if (source == null) return null;
        AcademicEventDTO dto = new AcademicEventDTO();
        populate(source, dto);
        return dto;
    }

    @Override
    public void populate(AcademicEventModel source, AcademicEventDTO target) {
        if (source == null || target == null) return;

        target.setId(source.getId());
        target.setAcademicUnitId(source.getAcademicUnit() != null ? source.getAcademicUnit().getId() : null);
        target.setType(source.getType());
        target.setName(source.getName());
        target.setDate(source.getDate());
        target.setDetails(source.getDetails());
    }

    public AcademicEventModel toModel(AcademicEventDTO dto, AcademicModel academicUnit) {
        if (dto == null) return null;
        AcademicEventModel model = new AcademicEventModel();
        model.setAcademicUnit(academicUnit);
        model.setType(dto.getType());
        model.setName(dto.getName());
        model.setDate(dto.getDate());
        model.setDetails(dto.getDetails());
        return model;
    }
}
