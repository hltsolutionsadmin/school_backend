package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.ExamTypeDTO;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.hlt.usermanagement.model.ExamTypeModel;
import com.hlt.usermanagement.populator.ExamTypePopulator;
import com.hlt.usermanagement.repository.ExamTypeRepository;
import com.hlt.usermanagement.services.ExamTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for managing exam types (e.g., Midterm, Final, Unit Test).
 */
@Service
@RequiredArgsConstructor
public class ExamTypeServiceImpl implements ExamTypeService {

    private final ExamTypeRepository examTypeRepository;
    private final ExamTypePopulator populator;

    /**
     * Creates a new exam type.
     */
    @Override
    public ExamTypeDTO createExamType(ExamTypeDTO dto) {
        if (dto.getTypeName() == null || dto.getTypeName().trim().isEmpty()) {
            throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Exam type name must be provided.");
        }

        // Prevent duplicate names
        Optional<ExamTypeModel> existing = examTypeRepository.findAll().stream()
                .filter(type -> type.getTypeName().equalsIgnoreCase(dto.getTypeName()))
                .findFirst();
        if (existing.isPresent()) {
            throw new HltCustomerException(ErrorCode.ALREADY_EXISTS, "Exam type already exists with name: " + dto.getTypeName());
        }

        ExamTypeModel model = new ExamTypeModel();
        populator.populateDtoToModel(dto, model);

        ExamTypeModel saved = examTypeRepository.save(model);

        ExamTypeDTO response = new ExamTypeDTO();
        populator.populateModelToDto(saved, response);
        return response;
    }

    /**
     * Updates an existing exam type.
     */
    @Override
    public ExamTypeDTO updateExamType(Long id, ExamTypeDTO dto) {
        ExamTypeModel model = examTypeRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.NOT_FOUND, "Exam type not found with ID: " + id));

        if (dto.getTypeName() != null && !dto.getTypeName().trim().isEmpty()) {
            model.setTypeName(dto.getTypeName());
        }
        if (dto.getDescription() != null) {
            model.setDescription(dto.getDescription());
        }

        ExamTypeModel updated = examTypeRepository.save(model);

        ExamTypeDTO response = new ExamTypeDTO();
        populator.populateModelToDto(updated, response);
        return response;
    }

    /**
     * Retrieves all exam types.
     */
    @Override
    public List<ExamTypeDTO> getAllExamTypes() {
        return examTypeRepository.findAll().stream().map(model -> {
            ExamTypeDTO dto = new ExamTypeDTO();
            populator.populateModelToDto(model, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * Deletes an exam type by ID.
     */
    @Override
    public void deleteExamType(Long id) {
        if (!examTypeRepository.existsById(id)) {
            throw new HltCustomerException(ErrorCode.NOT_FOUND, "Exam type not found with ID: " + id);
        }
        examTypeRepository.deleteById(id);
    }

    /**
     * Finds an exam type by name.
     */
    @Override
    public ExamTypeDTO getExamTypeByName(String typeName) {
        ExamTypeModel model = examTypeRepository.findAll().stream()
                .filter(type -> type.getTypeName().equalsIgnoreCase(typeName))
                .findFirst()
                .orElseThrow(() -> new HltCustomerException(ErrorCode.NOT_FOUND, "Exam type not found with name: " + typeName));

        ExamTypeDTO dto = new ExamTypeDTO();
        populator.populateModelToDto(model, dto);
        return dto;
    }
}
