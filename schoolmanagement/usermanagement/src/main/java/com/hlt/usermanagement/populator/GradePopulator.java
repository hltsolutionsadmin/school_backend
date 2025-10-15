package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.GradeDTO;
import com.hlt.usermanagement.model.GradeModel;
import com.hlt.usermanagement.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GradePopulator {


    public GradeDTO toDTO(GradeModel entity) {
        if (entity == null) return null;

        GradeDTO dto = new GradeDTO();
        dto.setId(entity.getId());
        Optional.ofNullable(entity.getStudent())
                .map(UserModel::getId)
                .ifPresent(dto::setStudentId);

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

    public void updateEntity(GradeDTO dto, GradeModel entity) {
        if (dto.getSubject() != null) entity.setSubject(dto.getSubject());
        if (dto.getScore() != null) entity.setScore(dto.getScore());
        if (dto.getRemarks() != null) entity.setRemarks(dto.getRemarks());

    }
}