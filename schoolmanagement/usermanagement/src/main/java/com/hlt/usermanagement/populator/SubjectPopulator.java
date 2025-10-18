package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.SubjectDTO;
import com.hlt.usermanagement.model.B2BUnitModel;
import com.hlt.usermanagement.model.SubjectModel;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;

@Component
public class SubjectPopulator implements Populator<SubjectModel, SubjectDTO> {

    @Override
    public void populate(SubjectModel source, SubjectDTO target) {
        if (source == null || target == null) return;

        target.setId(source.getId());
        target.setName(source.getName());
        target.setCode(source.getCode());
        target.setDescription(source.getDescription());
        target.setCredits(source.getCredits());
        target.setActive(source.getActive());
        target.setB2bUnitId(
                source.getB2bUnit() != null ? source.getB2bUnit().getId() : null
        );
    }

    public SubjectDTO toDTO(SubjectModel source) {
        if (source == null) return null;
        SubjectDTO dto = new SubjectDTO();
        populate(source, dto);
        return dto;
    }

    public SubjectModel toModel(SubjectDTO dto) {
        if (dto == null) return null;
        SubjectModel model = new SubjectModel();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setCode(dto.getCode());
        model.setDescription(dto.getDescription());
        model.setCredits(dto.getCredits());
        model.setActive(dto.getActive());

        if (dto.getB2bUnitId() != null) {
            B2BUnitModel unit = new B2BUnitModel();
            unit.setId(dto.getB2bUnitId());
            model.setB2bUnit(unit);
        }

        return model;
    }
}
