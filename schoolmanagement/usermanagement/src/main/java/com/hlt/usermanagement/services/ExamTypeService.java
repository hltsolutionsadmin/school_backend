package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.ExamTypeDTO;
import java.util.List;

/**
 * Service interface for managing exam types (e.g., Midterm, Final, Unit Test).
 */
public interface ExamTypeService {

    ExamTypeDTO createExamType(ExamTypeDTO dto);

    ExamTypeDTO updateExamType(Long id, ExamTypeDTO dto);

    List<ExamTypeDTO> getAllExamTypes();

    void deleteExamType(Long id);

    /**
     * Optional helper: find an exam type by name
     */
    ExamTypeDTO getExamTypeByName(String typeName);
}
