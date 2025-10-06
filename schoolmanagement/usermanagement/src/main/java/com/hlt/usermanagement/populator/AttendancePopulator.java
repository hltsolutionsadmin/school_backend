package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.AttendanceDTO;
import com.hlt.usermanagement.model.AttendanceModel;
import com.hlt.usermanagement.model.StudentModel;
import com.hlt.usermanagement.repository.StudentRepository;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AttendancePopulator {

    private final StudentRepository studentRepository;

    public AttendanceDTO toDTO(AttendanceModel entity) {
        if (entity == null) return null;

        AttendanceDTO dto = new AttendanceDTO();
        dto.setId(entity.getId());

        Optional.ofNullable(entity.getStudent())
                .map(StudentModel::getId)
                .ifPresent(dto::setStudentId);

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
        if (dto.getStudentId() != null) {
            StudentModel student = studentRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new HltCustomerException(ErrorCode.STUDENT_NOT_FOUND));
            entity.setStudent(student);
        } else {
            throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Student ID must be provided for an attendance record.");
        }

        return entity;
    }

    public void updateEntity(AttendanceDTO dto, AttendanceModel entity) {

        if (dto.getDate() != null) {
            entity.setDate(dto.getDate());
        }
        if (dto.getPresent() != null) {
            entity.setPresent(dto.getPresent());
        }

    }
}