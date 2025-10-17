package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.ResultDTO;
import com.hlt.usermanagement.model.ExamModel;
import com.hlt.usermanagement.model.ResultModel;
import com.hlt.usermanagement.model.UserModel;
import com.hlt.usermanagement.populator.ResultPopulator;
import com.hlt.usermanagement.repository.ExamRepository;
import com.hlt.usermanagement.repository.ResultRepository;
import com.hlt.usermanagement.repository.UserRepository;
import com.hlt.usermanagement.services.ResultService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final ExamRepository examRepository;
    private final UserRepository userRepository;
    private final ResultPopulator resultPopulator;

    @Override
    public ResultDTO addResult(ResultDTO dto) {
        ExamModel exam = examRepository.findById(dto.getExamId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.EXAM_NOT_FOUND));

        UserModel student = userRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND));

        ResultModel result = ResultModel.builder()
                .exam(exam)
                .student(student)
                .marksObtained(dto.getMarksObtained())
                .maxMarks(dto.getMaxMarks())
                .grade(dto.getGrade())
                .build();

        result = resultRepository.save(result);
        return resultPopulator.toDTO(result);
    }

    @Override
    public List<ResultDTO> getResultsByExam(Long examId) {
        return resultRepository.findByExam_Id(examId).stream()
                .map(resultPopulator::toDTO)
                .toList();
    }

    @Override
    public List<ResultDTO> getResultsByStudent(Long studentId) {
        return resultRepository.findByStudent_Id(studentId).stream()
                .map(resultPopulator::toDTO)
                .toList();
    }

    @Override
    public ResultDTO updateResult(Long resultId, ResultDTO dto) {
        ResultModel result = resultRepository.findById(resultId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.RESULT_NOT_FOUND));

        result.setMarksObtained(dto.getMarksObtained());
        result.setMaxMarks(dto.getMaxMarks());
        result.setGrade(dto.getGrade());

        result = resultRepository.save(result);
        return resultPopulator.toDTO(result);
    }

    @Override
    public void deleteResult(Long resultId) {
        ResultModel result = resultRepository.findById(resultId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.RESULT_NOT_FOUND));
        resultRepository.delete(result);
    }
}
