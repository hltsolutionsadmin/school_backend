package com.hlt.usermanagement.services.impl;


import com.hlt.usermanagement.dto.SubjectDTO;
import com.hlt.usermanagement.model.SubjectModel;
import com.hlt.usermanagement.populator.SubjectPopulator;
import com.hlt.usermanagement.repository.SubjectRepository;
import com.hlt.usermanagement.services.SubjectService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectPopulator subjectPopulator;

    @Override
    public SubjectDTO createSubject(SubjectDTO dto) {
        SubjectModel model = subjectPopulator.toModel(dto);
        return subjectPopulator.toDTO(subjectRepository.save(model));
    }

    @Override
    public SubjectDTO updateSubject(Long id, SubjectDTO dto) {
        SubjectModel existing = subjectRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.SUBJECT_NOT_FOUND));

        existing.setName(dto.getName());
        existing.setCode(dto.getCode());
        existing.setDescription(dto.getDescription());
        existing.setCredits(dto.getCredits());
        existing.setActive(dto.getActive());

        return subjectPopulator.toDTO(subjectRepository.save(existing));
    }

    @Override
    public SubjectDTO getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .map(subjectPopulator::toDTO)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.SUBJECT_NOT_FOUND));
    }

    @Override
    public Page<SubjectDTO> getSubjects(Long b2bUnitId, Pageable pageable) {
        return subjectRepository.findByB2bUnit_Id(b2bUnitId, pageable)
                .map(subjectPopulator::toDTO);
    }

    @Override
    public void deleteSubject(Long id) {
        SubjectModel model = subjectRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.SUBJECT_NOT_FOUND));
        subjectRepository.delete(model);
    }
}
