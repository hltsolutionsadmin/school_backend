package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.ExamDto;
import com.hlt.usermanagement.model.*;
import com.hlt.usermanagement.populator.ExamPopulator;
import com.hlt.usermanagement.repository.*;
import com.hlt.usermanagement.services.ExamService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of ExamService for managing exams.
 * Uses Populator for converting between DTO and Model.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final ClassRepository classRepository;
    private final SubjectRepository subjectRepository;
    private final ExamTypeRepository typeRepository;
    private final ExamPopulator examPopulator;

    @Override
    public ExamDto createExam(ExamDto dto) {
        validateDto(dto);

        ExamModel model = new ExamModel();
        populateDependencies(dto, model);

        // Use populator to set remaining basic fields
        examPopulator.populateDtoToModel(dto, model);

        ExamModel saved = examRepository.save(model);
        return examPopulator.populateModelToDto(saved);
    }

    @Override
    public ExamDto updateExam(Long id, ExamDto dto) {
        if (id == null) throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Exam ID must be provided.");
        validateDto(dto);

        ExamModel model = examRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.NOT_FOUND, "Exam not found with id: " + id));

        populateDependencies(dto, model);
        examPopulator.populateDtoToModel(dto, model);

        ExamModel updated = examRepository.save(model);
        return examPopulator.populateModelToDto(updated);
    }

    @Override
    public void deleteExam(Long id) {
        if (id == null) throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Exam ID must be provided.");
        if (!examRepository.existsById(id))
            throw new HltCustomerException(ErrorCode.NOT_FOUND, "Exam not found with id: " + id);

        examRepository.deleteById(id);
    }

    @Override
    public List<ExamDto> getAllExams() {
        return examRepository.findAll().stream()
                .map(examPopulator::populateModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamDto> getExamsByClassId(Long classId) {
        if (classId == null) throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Class ID must be provided.");
        return examRepository.findByClassModel_Id(classId).stream()
                .map(examPopulator::populateModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamDto> getExamsBySubjectId(Long subjectId) {
        if (subjectId == null) throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Subject ID must be provided.");
        return examRepository.findBySubjectModel_Id(subjectId).stream()
                .map(examPopulator::populateModelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExamDto> getExamsByExamTypeId(Long examTypeId) {
        if (examTypeId == null) throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Exam Type ID must be provided.");
        return examRepository.findByExamType_Id(examTypeId).stream()
                .map(examPopulator::populateModelToDto)
                .collect(Collectors.toList());
    }

    /**
     * Validate essential DTO fields
     */
    private void validateDto(ExamDto dto) {
        if (dto == null)
            throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Exam details cannot be null.");
        if (dto.getExamName() == null || dto.getExamName().isEmpty())
            throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Exam name must be provided.");
        if (dto.getMaxMarks() == null || dto.getMinMarks() == null)
            throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Max and Min marks must be provided.");
        if (dto.getExamDateTime() == null)
            throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Exam date/time must be provided.");
        if (dto.getClassId() == null)
            throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Class ID must be provided.");
        if (dto.getSubjectId() == null)
            throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Subject ID must be provided.");
        if (dto.getExamTypeId() == null)
            throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Exam type ID must be provided.");
    }

    /**
     * Populate related entities from DTO
     */
    private void populateDependencies(ExamDto dto, ExamModel model) {
        model.setClassModel(classRepository.findById(dto.getClassId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.NOT_FOUND,
                        "Class not found with id: " + dto.getClassId())));

        model.setSubjectModel(subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.NOT_FOUND,
                        "Subject not found with id: " + dto.getSubjectId())));

        model.setExamType(typeRepository.findById(dto.getExamTypeId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.NOT_FOUND,
                        "Exam type not found with id: " + dto.getExamTypeId())));

        model.setExamDateTime(dto.getExamDateTime());
    }
}
