package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.GradeDTO;
import com.hlt.usermanagement.model.GradeModel;
import org.springframework.stereotype.Component;

@Component
public class GradePopulator {

    public GradeDTO toDTO(GradeModel entity) {
        if (entity == null) return null;
        GradeDTO dto = new GradeDTO();
        dto.setId(entity.getId());
        dto.setStudentId(entity.getStudent().getId());
        dto.setSubject(entity.getSubject());
        dto.setScore(entity.getScore());
        dto.setRemarks(entity.getRemarks());
        return dto;
    }

    public GradeModel toEntity(GradeDTO dto) {
        if (dto == null) return null;
        GradeModel entity = new GradeModel();
        entity.setId(dto.getId());
        entity.setSubject(dto.getSubject());
        entity.setScore(dto.getScore());
        entity.setRemarks(dto.getRemarks());
        return entity;
    }
}
