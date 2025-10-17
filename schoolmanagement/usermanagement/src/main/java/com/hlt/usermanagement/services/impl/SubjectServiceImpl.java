package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.SubjectDTO;
import com.hlt.usermanagement.model.SubjectModel;
import com.hlt.usermanagement.repository.SubjectRepository;
import com.hlt.usermanagement.services.SubjectService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;


    /**
     * Add new subject to a class
     */
    @Override
    public SubjectDTO addSubject(SubjectDTO subjectDTO) {
        log.info("Adding subject '{}' with code '{}' to class {}", subjectDTO.getName(), subjectDTO.getCode(), subjectDTO.getClassId());



        if (subjectRepository.existsByNameAndClassModelId(subjectDTO.getName(), subjectDTO.getClassId())) {
            throw new HltCustomerException(ErrorCode.SUBJECT_ALREADY_EXISTS);
        }
        if (subjectRepository.existsByCodeAndClassModelId(subjectDTO.getCode(), subjectDTO.getClassId())) {
            throw new HltCustomerException(ErrorCode.SUBJECT_ALREADY_EXISTS);
        }

        SubjectModel subject = new SubjectModel();
        subject.setName(subjectDTO.getName());
        subject.setCode(subjectDTO.getCode());


        SubjectModel saved = subjectRepository.save(subject);
        log.info("Subject '{}' created successfully with ID {}", saved.getName(), saved.getId());

        return mapToDTO(saved);
    }

    /**
     * Update existing subject
     */
    @Override
    public SubjectDTO updateSubject(Long id, SubjectDTO subjectDTO) {
        log.info("Updating subject with ID {}", id);

            SubjectModel subject = subjectRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.SUBJECT_NOT_FOUND));

        if (!subject.getName().equals(subjectDTO.getName()) &&
                subjectRepository.existsByNameAndClassModelId(subjectDTO.getName(), subject.getClassModel().getId())) {
            throw new HltCustomerException(ErrorCode.SUBJECT_ALREADY_EXISTS);
        }

        if (!subject.getCode().equals(subjectDTO.getCode()) &&
                subjectRepository.existsByCodeAndClassModelId(subjectDTO.getCode(), subject.getClassModel().getId())) {
            throw new HltCustomerException(ErrorCode.SUBJECT_ALREADY_EXISTS);
        }

        subject.setName(subjectDTO.getName());
        subject.setCode(subjectDTO.getCode());


        SubjectModel updated = subjectRepository.save(subject);
        log.info("Subject with ID {} updated successfully", updated.getId());

        return mapToDTO(updated);
    }

    /**
     * Get subject details by ID
     */
    @Override
    @Transactional(readOnly = true)
    public SubjectDTO getSubjectById(Long id) {
        log.info("Fetching subject with ID {}", id);

        SubjectModel subject = subjectRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.SUBJECT_NOT_FOUND));

        return mapToDTO(subject);
    }

    /**
     * Delete subject by ID
     */
    @Override
    public void deleteSubject(Long id) {
        log.info("Deleting subject with ID {}", id);

        if (!subjectRepository.existsById(id)) {
            throw new HltCustomerException(ErrorCode.SUBJECT_NOT_FOUND);
        }

        subjectRepository.deleteById(id);
        log.info("Subject with ID {} deleted successfully", id);
    }

    /**
     * List all subjects in a class
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SubjectDTO> getSubjectsByClass(Long classId, Pageable pageable) {
        log.info("Fetching subjects for class ID {} with pagination", classId);

        return subjectRepository.findByClassModelId(classId, pageable)
                .map(this::mapToDTO);
    }



    /**
     * Convert Subject entity to DTO
     */
    private SubjectDTO mapToDTO(SubjectModel subject) {
        SubjectDTO dto = new SubjectDTO();
        dto.setId(subject.getId());
        dto.setName(subject.getName());
        dto.setCode(subject.getCode());
        dto.setClassId(subject.getClassModel().getId());
//        dto.setClassName(subject.getClassModel().getClassName());
        dto.setSection(subject.getClassModel().getSection());



        return dto;
    }
}
