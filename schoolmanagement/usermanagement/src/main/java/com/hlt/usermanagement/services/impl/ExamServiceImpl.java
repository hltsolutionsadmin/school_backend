package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.ExamDTO;
import com.hlt.usermanagement.dto.ExamTimetableDTO;
import com.hlt.usermanagement.model.AcademicModel;
import com.hlt.usermanagement.model.ExamModel;
import com.hlt.usermanagement.model.ExamTimetableModel;
import com.hlt.usermanagement.populator.ExamPopulator;
import com.hlt.usermanagement.populator.ExamTimetablePopulator;
import com.hlt.usermanagement.repository.AcademicRepository;
import com.hlt.usermanagement.repository.ExamRepository;
import com.hlt.usermanagement.repository.ExamTimetableRepository;
import com.hlt.usermanagement.services.ExamService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final AcademicRepository academicRepository;
    private final ExamTimetableRepository timetableRepository;
    private final ExamPopulator examPopulator;
    private final ExamTimetablePopulator timetablePopulator;

    @Override
    public ExamDTO createExam(ExamDTO examDTO) {
        AcademicModel academic = academicRepository.findById(examDTO.getAcademicId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.ACADEMIC_NOT_FOUND));

        ExamModel exam = ExamModel.builder()
                .name(examDTO.getName())
                .academic(academic)
                .build();

        exam = examRepository.save(exam);
        return examPopulator.toDTO(exam);
    }

    @Override
    public ExamDTO getExamById(Long examId) {
        ExamModel exam = examRepository.findById(examId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.EXAM_NOT_FOUND));
        return examPopulator.toDTO(exam);
    }

    @Override
    public List<ExamDTO> getExamsByAcademic(Long academicId) {
        return examRepository.findByAcademic_Id(academicId).stream()
                .map(examPopulator::toDTO)
                .toList();
    }

    @Override
    public ExamDTO updateExam(Long examId, ExamDTO examDTO) {
        ExamModel exam = examRepository.findById(examId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.EXAM_NOT_FOUND));

        exam.setName(examDTO.getName());
        exam = examRepository.save(exam);
        return examPopulator.toDTO(exam);
    }

    @Override
    public void deleteExam(Long examId) {
        ExamModel exam = examRepository.findById(examId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.EXAM_NOT_FOUND));
        examRepository.delete(exam);
    }

    // ---------- Exam Timetable CRUD ----------
    @Override
    public ExamTimetableDTO addExamToTimetable(Long examId, ExamTimetableDTO dto) {
        ExamModel exam = examRepository.findById(examId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.EXAM_NOT_FOUND));

        ExamTimetableModel timetable = ExamTimetableModel.builder()
                .exam(exam)
                .subject(dto.getSubject())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .venue(dto.getVenue())
                .build();

        timetable = timetableRepository.save(timetable);
        return timetablePopulator.toDTO(timetable);
    }

    @Override
    public List<ExamTimetableDTO> getTimetableByExam(Long examId) {
        return timetableRepository.findByExam_Id(examId).stream()
                .map(timetablePopulator::toDTO)
                .toList();
    }

    @Override
    public ExamTimetableDTO updateTimetable(Long timetableId, ExamTimetableDTO dto) {
        ExamTimetableModel timetable = timetableRepository.findById(timetableId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.TIMETABLE_NOT_FOUND));

        timetable.setSubject(dto.getSubject());
        timetable.setStartTime(dto.getStartTime());
        timetable.setEndTime(dto.getEndTime());
        timetable.setVenue(dto.getVenue());

        timetable = timetableRepository.save(timetable);
        return timetablePopulator.toDTO(timetable);
    }

    @Override
    public void deleteTimetable(Long timetableId) {
        ExamTimetableModel timetable = timetableRepository.findById(timetableId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.TIMETABLE_NOT_FOUND));
        timetableRepository.delete(timetable);
    }
}
