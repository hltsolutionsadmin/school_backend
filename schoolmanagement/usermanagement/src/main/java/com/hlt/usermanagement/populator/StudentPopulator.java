package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.StudentDTO;
import com.hlt.usermanagement.model.StudentModel;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;

@Component
public class StudentPopulator implements Populator<StudentModel, StudentDTO> {

    @Override
    public void populate(StudentModel source, StudentDTO target) {
        if (source == null || target == null) return;

        target.setId(source.getId());

        if (source.getUser() != null) {
            target.setUserId(source.getUser().getId());
            target.setUserName(source.getUser().getFullName());
        }

        if (source.getSchool() != null) {
            target.setSchoolId(source.getSchool().getId());
            target.setSchoolName(source.getSchool().getName());
        }

        if (source.getClassModel() != null) {
            target.setClassId(source.getClassModel().getId());
            target.setClassName(source.getClassModel().getClassName());
            target.setSection(source.getClassModel().getSection());
        }

        target.setRollNumber(source.getRollNumber());
        target.setAdmissionDate(source.getAdmissionDate());
        target.setActive(source.getActive());
    }

    public StudentDTO toDTO(StudentModel source) {
        if (source == null) return null;
        StudentDTO dto = new StudentDTO();
        populate(source, dto);
        return dto;
    }
}
