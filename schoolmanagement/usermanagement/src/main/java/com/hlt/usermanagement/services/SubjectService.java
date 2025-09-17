package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.SubjectDTO;

import java.util.List;

public interface SubjectService {
    SubjectDTO getSubjectById(Long id);
    List<SubjectDTO> getSubjectsByClass(Long classId);
}
