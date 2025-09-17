package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.ClassDTO;
import com.hlt.usermanagement.dto.StudentDTO;
import com.hlt.usermanagement.dto.SubjectDTO;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClassService {

    ClassDTO createClass(ClassDTO dto);

    ClassDTO updateClass(Long id, ClassDTO dto);

    ClassDTO getClassById(Long id);

    void deleteClass(Long id);

    List<StudentDTO> getStudentsInClass(Long classId, Pageable pageable);

    List<SubjectDTO> getSubjectsInClass(Long classId, Pageable pageable);
}

