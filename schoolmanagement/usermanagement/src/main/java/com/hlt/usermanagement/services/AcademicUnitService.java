package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.AcademicUnitDTO;
import com.hlt.usermanagement.dto.StudentDTO;
import com.hlt.usermanagement.dto.SubjectDTO;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AcademicUnitService {

    AcademicUnitDTO createClass(AcademicUnitDTO dto);

    AcademicUnitDTO updateClass(Long id, AcademicUnitDTO dto);

    AcademicUnitDTO getClassById(Long id);

    void deleteClass(Long id);

    List<StudentDTO> getStudentsInClass(Long classId, Pageable pageable);

    List<SubjectDTO> getSubjectsInClass(Long classId, Pageable pageable);
}

