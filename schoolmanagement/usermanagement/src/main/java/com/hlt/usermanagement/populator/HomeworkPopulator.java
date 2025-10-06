package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.HomeworkDTO;
import com.hlt.usermanagement.model.HomeworkModel;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class HomeworkPopulator implements Populator<HomeworkModel, HomeworkDTO> {

    @Override
    public void populate(HomeworkModel source, HomeworkDTO target) {
        target.setId(source.getId());
        target.setStudentId(source.getStudent().getId());
        target.setStaffId(source.getStaff().getId());
        target.setSchoolId(source.getSchool().getId());
        target.setSubject(source.getSubject());
        target.setDescription(source.getDescription());
        target.setAssignDate(source.getAssignDate());
        target.setDueDate(source.getDueDate());
        target.setStatus(source.getStatus());
        target.setRemarks(source.getRemarks());
        target.setCreatedAt(LocalDate.from(source.getCreatedAt()));
        target.setUpdatedAt(LocalDate.from(source.getUpdatedAt()));
    }
}
