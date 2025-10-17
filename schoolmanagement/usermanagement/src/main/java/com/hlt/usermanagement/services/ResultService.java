package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.ResultDTO;

import java.util.List;

public interface ResultService {

    ResultDTO addResult(ResultDTO dto);

    List<ResultDTO> getResultsByExam(Long examId);

    List<ResultDTO> getResultsByStudent(Long studentId);

    ResultDTO updateResult(Long resultId, ResultDTO dto);

    void deleteResult(Long resultId);
}
