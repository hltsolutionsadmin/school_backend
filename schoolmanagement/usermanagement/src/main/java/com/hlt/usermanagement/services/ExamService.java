package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.ExamDto;
import java.util.List;

public interface ExamService {

    ExamDto createExam(ExamDto dto);

    ExamDto updateExam(Long id, ExamDto dto);

    void deleteExam(Long id);

    List<ExamDto> getAllExams();

    List<ExamDto> getExamsByClassId(Long classId);

    List<ExamDto> getExamsBySubjectId(Long subjectId);

    List<ExamDto> getExamsByExamTypeId(Long examTypeId);
}
