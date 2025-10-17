package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.ResultDTO;
import com.hlt.usermanagement.model.ResultModel;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;

@Component
public class ResultPopulator implements Populator<ResultModel, ResultDTO> {

    private final ReportCardPopulator reportCardPopulator;

    public ResultPopulator(ReportCardPopulator reportCardPopulator) {
        this.reportCardPopulator = reportCardPopulator;
    }

    public ResultDTO toDTO(ResultModel source) {
        if (source == null) return null;
        ResultDTO dto = new ResultDTO();
        populate(source, dto);
        return dto;
    }

    @Override
    public void populate(ResultModel source, ResultDTO target) {
        if (source == null || target == null) return;
        target.setId(source.getId());
        target.setExamId(source.getExam().getId());
        target.setStudentId(source.getStudent().getId());
        target.setMarksObtained(source.getMarksObtained());
        target.setMaxMarks(source.getMaxMarks());
        target.setGrade(source.getGrade());

        if (source.getReportCard() != null) {
            target.setReportCard(reportCardPopulator.toDTO(source.getReportCard()));
        }
    }
}
