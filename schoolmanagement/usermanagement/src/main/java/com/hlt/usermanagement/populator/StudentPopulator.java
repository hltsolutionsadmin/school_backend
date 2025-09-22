package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.StudentDTO;
import com.hlt.usermanagement.model.ClassModel;
import com.hlt.usermanagement.model.SchoolModel;
import com.hlt.usermanagement.model.StudentModel;
import com.hlt.usermanagement.model.UserModel;
import com.hlt.usermanagement.repository.ClassRepository;
import com.hlt.usermanagement.repository.SchoolRepository;
import com.hlt.usermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentPopulator {

    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;
    private final ClassRepository classRepository;

    public StudentModel toEntity(StudentDTO dto) {
        StudentModel entity = new StudentModel();
        entity.setId(dto.getId());
        entity.setRollNumber(dto.getRollNumber());
        entity.setAdmissionDate(dto.getAdmissionDate());
        entity.setActive(dto.getActive());

        if (dto.getUserId() != null) {
            UserModel user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id " + dto.getUserId()));
            entity.setUser(user);
        }

        if (dto.getSchoolId() != null) {
            SchoolModel school = schoolRepository.findById(dto.getSchoolId())
                    .orElseThrow(() -> new RuntimeException("School not found with id " + dto.getSchoolId()));
            entity.setSchool(school);
        }

        if (dto.getClassId() != null) {
            ClassModel classModel = classRepository.findById(dto.getClassId())
                    .orElseThrow(() -> new RuntimeException("Class not found with id " + dto.getClassId()));
            entity.setClassModel(classModel);
        }

        return entity;
    }

    public StudentDTO toDTO(StudentModel entity) {
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setRollNumber(entity.getRollNumber());
        dto.setAdmissionDate(entity.getAdmissionDate());
        dto.setActive(entity.getActive());

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

        if (dto.getUserId() != null) {
            UserModel user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id " + dto.getUserId()));
            student.setUser(user);
        }

        if (dto.getSchoolId() != null) {
            SchoolModel school = schoolRepository.findById(dto.getSchoolId())
                    .orElseThrow(() -> new RuntimeException("School not found with id " + dto.getSchoolId()));
            student.setSchool(school);
        }

        if (dto.getClassId() != null) {
            ClassModel classModel = classRepository.findById(dto.getClassId())
                    .orElseThrow(() -> new RuntimeException("Class not found with id " + dto.getClassId()));
            student.setClassModel(classModel);
        }
    }
}
