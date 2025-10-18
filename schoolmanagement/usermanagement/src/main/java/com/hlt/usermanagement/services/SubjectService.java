package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.SubjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubjectService {

    SubjectDTO createSubject(SubjectDTO dto);

    SubjectDTO updateSubject(Long id, SubjectDTO dto);

    SubjectDTO getSubjectById(Long id);

    Page<SubjectDTO> getSubjects(Long b2bUnitId, Pageable pageable);

    void deleteSubject(Long id);
}
