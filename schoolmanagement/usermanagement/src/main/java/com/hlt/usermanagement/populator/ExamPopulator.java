package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.ExamDTO;
import com.hlt.usermanagement.model.ExamModel;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;

@Component
public class ExamPopulator implements Populator<ExamModel, ExamDTO> {

    private final ExamTimetablePopulator timetablePopulator;
    private final ResultPopulator resultPopulator;

    public ExamPopulator(ExamTimetablePopulator timetablePopulator, ResultPopulator resultPopulator) {
        this.timetablePopulator = timetablePopulator;
        this.resultPopulator = resultPopulator;
    }

    public ExamDTO toDTO(ExamModel source) {
        if (source == null) return null;
        ExamDTO dto = new ExamDTO();
        populate(source, dto);
        return dto;
    }

    @Override
    public void populate(ExamModel source, ExamDTO target) {
        if (source == null || target == null) return;
        target.setId(source.getId());
        target.setName(source.getName());
        target.setAcademicId(source.getAcademic().getId());

        if (source.getTimetable() != null) {
            target.setTimetable(source.getTimetable().stream()
                    .map(timetablePopulator::toDTO)
                    .toList());
        }

        if (source.getResults() != null) {
            target.setResults(source.getResults().stream()
                    .map(resultPopulator::toDTO)
                    .toList());
        }
    }
}
