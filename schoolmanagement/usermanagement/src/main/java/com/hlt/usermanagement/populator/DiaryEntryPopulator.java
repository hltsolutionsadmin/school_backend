package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.DiaryEntryDTO;
import com.hlt.usermanagement.model.DiaryEntryModel;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;

@Component
public class DiaryEntryPopulator implements Populator<DiaryEntryModel, DiaryEntryDTO> {

    @Override
    public void populate(DiaryEntryModel source, DiaryEntryDTO target) {
        target.setId(source.getId());
        target.setStudentId(source.getStudent().getId());
        target.setStaffId(source.getStaff().getId());
        target.setSubject(source.getSubject());
        target.setSubmissionNote(source.getNote());
        target.setEntryDate(source.getEntryDate());
        target.setType(source.getClass().getSimpleName());
    }
}
