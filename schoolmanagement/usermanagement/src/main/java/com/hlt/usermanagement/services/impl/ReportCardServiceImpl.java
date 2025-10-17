package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.ReportCardDTO;
import com.hlt.usermanagement.model.AcademicModel;
import com.hlt.usermanagement.model.ReportCardModel;
import com.hlt.usermanagement.model.ResultModel;
import com.hlt.usermanagement.populator.ReportCardPopulator;
import com.hlt.usermanagement.repository.AcademicRepository;
import com.hlt.usermanagement.repository.ReportCardRepository;
import com.hlt.usermanagement.repository.ResultRepository;
import com.hlt.usermanagement.services.ReportCardService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportCardServiceImpl implements ReportCardService {

    private final ReportCardRepository reportCardRepository;
    private final ResultRepository resultRepository;
    private final AcademicRepository academicRepository;
    private final ReportCardPopulator reportCardPopulator;

    @Override
    public ReportCardDTO generateReportCard(Long resultId) {
        ResultModel result = resultRepository.findById(resultId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.RESULT_NOT_FOUND));

        AcademicModel academic = result.getExam().getAcademic();

        ReportCardModel reportCard = ReportCardModel.builder()
                .result(result)
                .academic(academic)
                .studentName(result.getStudent().getFullName())
                .percentage((result.getMarksObtained() / result.getMaxMarks()) * 100)
                .grade(result.getGrade())
                .remarks(generateRemarks(result.getMarksObtained(), result.getMaxMarks()))
                .build();

        reportCard = reportCardRepository.save(reportCard);
        return reportCardPopulator.toDTO(reportCard);
    }

    @Override
    public ReportCardDTO getReportCardById(Long reportCardId) {
        ReportCardModel reportCard = reportCardRepository.findById(reportCardId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.REPORT_CARD_NOT_FOUND));
        return reportCardPopulator.toDTO(reportCard);
    }

    @Override
    public List<ReportCardDTO> getReportCardsByAcademic(Long academicId) {
        return reportCardRepository.findByAcademic_IdAndStudentName(academicId, "")
                .stream()
                .map(reportCardPopulator::toDTO)
                .toList();
    }

    @Override
    public void deleteReportCard(Long reportCardId) {
        ReportCardModel reportCard = reportCardRepository.findById(reportCardId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.REPORT_CARD_NOT_FOUND));
        reportCardRepository.delete(reportCard);
    }

    private String generateRemarks(Double marks, Double maxMarks) {
        double percentage = (marks / maxMarks) * 100;
        if (percentage >= 90) return "Excellent";
        if (percentage >= 75) return "Very Good";
        if (percentage >= 60) return "Good";
        return "Needs Improvement";
    }
}
