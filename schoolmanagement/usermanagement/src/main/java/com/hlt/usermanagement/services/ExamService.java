package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.ExamDTO;
import com.hlt.usermanagement.dto.ExamTimetableDTO;

import java.util.List;

public interface ExamService {

    ExamDTO createExam(ExamDTO examDTO);

    ExamDTO getExamById(Long examId);

    List<ExamDTO> getExamsByAcademic(Long academicId);

    ExamDTO updateExam(Long examId, ExamDTO examDTO);

    void deleteExam(Long examId);

    ExamTimetableDTO addExamToTimetable(Long examId, ExamTimetableDTO timetableDTO);

    List<ExamTimetableDTO> getTimetableByExam(Long examId);

    ExamTimetableDTO updateTimetable(Long timetableId, ExamTimetableDTO dto);

    void deleteTimetable(Long timetableId);
}
