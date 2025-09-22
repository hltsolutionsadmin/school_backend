package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.AttendanceDTO;
import com.hlt.usermanagement.model.AttendanceModel;
import org.springframework.stereotype.Component;

@Component
public class AttendancePopulator {

    public AttendanceDTO toDTO(AttendanceModel entity) {
        if (entity == null) return null;

        AttendanceDTO dto = new AttendanceDTO();
        dto.setId(entity.getId());

        if (entity.getStudent() != null) {
            dto.setStudentId(entity.getStudent().getId());
        }

        dto.setDate(entity.getDate());
        dto.setPresent(entity.getPresent());
        return dto;
    }

    public AttendanceModel toEntity(AttendanceDTO dto) {
        if (dto == null) return null;

        AttendanceModel entity = new AttendanceModel();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setPresent(dto.getPresent());
        return entity;
    }
}
