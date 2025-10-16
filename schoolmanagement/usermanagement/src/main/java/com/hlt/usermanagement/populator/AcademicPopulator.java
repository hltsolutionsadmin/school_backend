package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.AcademicDTO;
import com.hlt.usermanagement.model.AcademicModel;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;

@Component
public class AcademicPopulator implements Populator<AcademicModel, AcademicDTO> {

    public AcademicDTO toDTO(AcademicModel source) {
        if (source == null) return null;
        AcademicDTO dto = new AcademicDTO();
        populate(source, dto);
        return dto;
    }

    @Override
    public void populate(AcademicModel source, AcademicDTO target) {
        if (source == null) return;

        target.setId(source.getId());
        target.setName(source.getName());
        target.setCode(source.getCode());
        target.setDescription(source.getDescription());
        target.setActive(source.getActive());
        target.setSortOrder(source.getSortOrder());
        target.setB2bUnitId(source.getB2bUnit() != null ? source.getB2bUnit().getId() : null);
        target.setSection(source.getSection());
        target.setCapacity(source.getCapacity());
        target.setStartDate(source.getStartDate());
        target.setEndDate(source.getEndDate());
        target.setSubjectCount(source.getSubjectCount());
        target.setTeacherCount(source.getTeacherCount());
        target.setNotes(source.getNotes());
    }
}
