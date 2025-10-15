package com.hlt.usermanagement.populator;

import com.hlt.usermanagement.dto.ExamDto;
import com.hlt.usermanagement.model.*;
import com.hlt.usermanagement.repository.*;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import org.springframework.stereotype.Component;

/**
 * Populator for converting between ExamDto and ExamModel.
 * Handles both DTO-to-Entity and Entity-to-DTO conversions.
 */
@Component
public class ExamPopulator {

    private final ClassRepository classRepository;
    private final SubjectRepository subjectRepository;
    private final ExamTypeRepository typeRepository;

    public ExamPopulator(ClassRepository classRepository,
                         SubjectRepository subjectRepository,
                         ExamTypeRepository typeRepository) {
        this.classRepository = classRepository;
        this.subjectRepository = subjectRepository;
        this.typeRepository = typeRepository;
    }

    /**
     * Populates ExamModel from ExamDto with repository lookups for related entities.
     *
     * @param dto   source DTO
     * @param model target model
     */
    public void populateDtoToModel(ExamDto dto, ExamModel model) {
        if (dto == null || model == null)
            throw new HltCustomerException(ErrorCode.INVALID_INPUT, "DTO or Model cannot be null");

        model.setExamName(dto.getExamName());
        model.setMaxMarks(dto.getMaxMarks());
        model.setMinMarks(dto.getMinMarks());
        model.setRemarks(dto.getRemarks());
        model.setExamDateTime(dto.getExamDateTime());

        // Class mapping
        if (dto.getClassId() == null)
            throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Class ID must be provided.");
        ClassModel classModel = classRepository.findById(dto.getClassId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.NOT_FOUND,
                        "Class not found with id: " + dto.getClassId()));
        model.setClassModel(classModel);

        // Subject mapping
        if (dto.getSubjectId() == null)
            throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Subject ID must be provided.");
        SubjectModel subjectModel = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.NOT_FOUND,
                        "Subject not found with id: " + dto.getSubjectId()));
        model.setSubjectModel(subjectModel);

        // Exam Type mapping
        if (dto.getExamTypeId() == null)
            throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Exam Type ID must be provided.");
        ExamTypeModel typeModel = typeRepository.findById(dto.getExamTypeId())
                .orElseThrow(() -> new HltCustomerException(ErrorCode.NOT_FOUND,
                        "Exam type not found with id: " + dto.getExamTypeId()));
        model.setExamType(typeModel);
    }

    /**
     * Populates ExamDto from ExamModel.
     *
     * @param model source model
     * @return populated DTO
     */
    public ExamDto populateModelToDto(ExamModel model) {
        if (model == null)
            throw new HltCustomerException(ErrorCode.INVALID_INPUT, "Exam model cannot be null");

        ExamDto dto = new ExamDto();
        dto.setId(model.getId());
        dto.setExamName(model.getExamName());
        dto.setMaxMarks(model.getMaxMarks());
        dto.setMinMarks(model.getMinMarks());
        dto.setRemarks(model.getRemarks());
        dto.setExamDateTime(model.getExamDateTime());

        if (model.getClassModel() != null) {
            dto.setClassId(model.getClassModel().getId());
            dto.setClassName(model.getClassModel().getClassName());
            dto.setSection(model.getClassModel().getSection());
        }

        if (model.getSubjectModel() != null) {
            dto.setSubjectId(model.getSubjectModel().getId());
            dto.setSubjectName(model.getSubjectModel().getName());
        }

        if (model.getExamType() != null) {
            dto.setExamTypeId(model.getExamType().getId());
            dto.setExamTypeName(model.getExamType().getTypeName());
        }

        return dto;
    }
}
