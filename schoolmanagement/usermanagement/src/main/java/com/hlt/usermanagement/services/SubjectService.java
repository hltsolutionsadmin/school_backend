package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.SubjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubjectService {

    SubjectDTO addSubject(SubjectDTO subjectDTO);

    SubjectDTO updateSubject(Long id, SubjectDTO subjectDTO);

    SubjectDTO getSubjectById(Long id);

    void deleteSubject(Long id);

    Page<SubjectDTO> getSubjectsByClass(Long classId, Pageable pageable);
}
