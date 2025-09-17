package com.hlt.usermanagement.services;


import com.hlt.usermanagement.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO getStudentById(Long id);
    List<StudentDTO> getStudentsBySchool(Long schoolId);
}

