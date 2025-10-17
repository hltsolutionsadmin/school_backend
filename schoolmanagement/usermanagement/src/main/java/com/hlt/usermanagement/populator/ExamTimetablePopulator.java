package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.ExamTimetableDTO;
import com.hlt.usermanagement.model.ExamTimetableModel;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;

@Component
public class ExamTimetablePopulator implements Populator<ExamTimetableModel, ExamTimetableDTO> {

    public ExamTimetableDTO toDTO(ExamTimetableModel source) {
        if (source == null) return null;
        ExamTimetableDTO dto = new ExamTimetableDTO();
        populate(source, dto);
        return dto;
    }

    @Override
    public void populate(ExamTimetableModel source, ExamTimetableDTO target) {
        if (source == null || target == null) return;
        target.setId(source.getId());
        target.setExamId(source.getExam().getId());
        target.setSubject(source.getSubject());
        target.setStartTime(source.getStartTime());
        target.setEndTime(source.getEndTime());
        target.setVenue(source.getVenue());
    }
}
