package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.ReportCardDTO;

import java.util.List;

public interface ReportCardService {

    ReportCardDTO generateReportCard(Long resultId);

    ReportCardDTO getReportCardById(Long reportCardId);

    List<ReportCardDTO> getReportCardsByAcademic(Long academicId);

    void deleteReportCard(Long reportCardId);
}
