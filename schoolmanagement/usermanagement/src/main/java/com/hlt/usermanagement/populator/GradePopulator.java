package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.GradeDTO;
import com.hlt.usermanagement.model.GradeModel;
import com.hlt.usermanagement.model.StudentModel; // Required for mapping the foreign key
import com.hlt.usermanagement.repository.StudentRepository; // Required for fetching StudentModel
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException; // Using custom exception
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GradePopulator {

    private final StudentRepository studentRepository;

    public GradeDTO toDTO(GradeModel entity) {
        if (entity == null) return null;

        GradeDTO dto = new GradeDTO();
        dto.setId(entity.getId());
        Optional.ofNullable(entity.getStudent())
                .map(StudentModel::getId)
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

        if (dto.getStudentId() != null) {
            StudentModel student = studentRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new HltCustomerException(ErrorCode.STUDENT_NOT_FOUND));
            entity.setStudent(student);
        } else {
            throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Student ID must be provided for a grade.");
        }

        return entity;
    }

    public void updateEntity(GradeDTO dto, GradeModel entity) {
        if (dto.getSubject() != null) entity.setSubject(dto.getSubject());
        if (dto.getScore() != null) entity.setScore(dto.getScore());
        if (dto.getRemarks() != null) entity.setRemarks(dto.getRemarks());

    }
}