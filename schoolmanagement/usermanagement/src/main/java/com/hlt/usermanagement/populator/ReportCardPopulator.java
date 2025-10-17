package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.ReportCardDTO;
import com.hlt.usermanagement.model.ReportCardModel;
import com.schoolmanagement.utils.Populator;
import org.springframework.stereotype.Component;

@Component
public class ReportCardPopulator implements Populator<ReportCardModel, ReportCardDTO> {

    public ReportCardDTO toDTO(ReportCardModel source) {
        if (source == null) return null;
        ReportCardDTO dto = new ReportCardDTO();
        populate(source, dto);
        return dto;
    }

    @Override
    public void populate(ReportCardModel source, ReportCardDTO target) {
        if (source == null || target == null) return;
        target.setId(source.getId());
        target.setResultId(source.getResult().getId());
        target.setAcademicId(source.getAcademic().getId());
        target.setStudentName(source.getStudentName());
        target.setRemarks(source.getRemarks());
        target.setPercentage(source.getPercentage());
        target.setGrade(source.getGrade());
    }
}
