package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.StudentDTO;
import com.hlt.usermanagement.model.ClassModel;
import com.hlt.usermanagement.model.SchoolModel;
import com.hlt.usermanagement.model.StudentModel;
import com.hlt.usermanagement.model.UserModel;
import com.hlt.usermanagement.repository.ClassRepository;
import com.hlt.usermanagement.repository.SchoolRepository;
import com.hlt.usermanagement.repository.UserRepository;
// Assuming these exception classes exist as per your service implementation
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StudentPopulator {

    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;
    private final ClassRepository classRepository;

    private final PersonalDetailsPopulator personalDetailsPopulator;
    private final AcademicDetailsPopulator academicDetailsPopulator;


    public StudentModel toEntity(StudentDTO dto) {
        StudentModel entity = new StudentModel();
        entity.setRollNumber(dto.getRollNumber());
        entity.setAdmissionDate(dto.getAdmissionDate());
        entity.setActive(Optional.ofNullable(dto.getActive()).orElse(Boolean.TRUE));
        if (dto.getPersonalDetails() != null) {
            entity.setPersonalDetails(personalDetailsPopulator.toEntity(dto.getPersonalDetails()));
        }
        if (dto.getAcademicDetails() != null) {
            entity.setAcademicDetails(academicDetailsPopulator.toEntity(dto.getAcademicDetails()));
        }
        mapForeignKeysToEntity(dto, entity);

        return entity;
    }

    public StudentDTO toDTO(StudentModel entity) {
        StudentDTO dto = new StudentDTO();

        dto.setId(entity.getId());
        dto.setRollNumber(entity.getRollNumber());
        dto.setAdmissionDate(entity.getAdmissionDate());
        dto.setActive(entity.getActive());
        if (entity.getPersonalDetails() != null) {
            dto.setPersonalDetails(personalDetailsPopulator.toDTO(entity.getPersonalDetails()));
        }
        if (entity.getAcademicDetails() != null) {
            dto.setAcademicDetails(academicDetailsPopulator.toDTO(entity.getAcademicDetails()));
        }
        if (entity.getUser() != null) {
            dto.setUserId(entity.getUser().getId());
            dto.setUserName(entity.getUser().getFullName());
        }

        if (entity.getSchool() != null) {
            dto.setSchoolId(entity.getSchool().getId());
            dto.setSchoolName(entity.getSchool().getName());
        }

        if (entity.getClassModel() != null) {
            dto.setClassId(entity.getClassModel().getId());
            dto.setClassName(entity.getClassModel().getClassName());
            dto.setSection(entity.getClassModel().getSection());
        }

        return dto;
    }

    public void updateEntity(StudentDTO dto, StudentModel student) {
        if (dto.getRollNumber() != null) student.setRollNumber(dto.getRollNumber());
        if (dto.getAdmissionDate() != null) student.setAdmissionDate(dto.getAdmissionDate());
        if (dto.getActive() != null) student.setActive(dto.getActive());
        if (dto.getPersonalDetails() != null && student.getPersonalDetails() != null) {
            personalDetailsPopulator.updateEntity(dto.getPersonalDetails(), student.getPersonalDetails());
        }
        if (dto.getAcademicDetails() != null && student.getAcademicDetails() != null) {
            academicDetailsPopulator.updateEntity(dto.getAcademicDetails(), student.getAcademicDetails());
        }
        mapForeignKeysToEntity(dto, student);
    }


    private void mapForeignKeysToEntity(StudentDTO dto, StudentModel entity) {
        if (dto.getUserId() != null) {
            UserModel user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND));
            entity.setUser(user);
        }

        if (dto.getSchoolId() != null) {
            SchoolModel school = schoolRepository.findById(dto.getSchoolId())
                    .orElseThrow(() -> new HltCustomerException(ErrorCode.SCHOOL_NOT_FOUND));
            entity.setSchool(school);
        }

        if (dto.getClassId() != null) {
            ClassModel classModel = classRepository.findById(dto.getClassId())
                    .orElseThrow(() -> new HltCustomerException(ErrorCode.CLASS_NOT_FOUND));
            entity.setClassModel(classModel);
        }
    }
}