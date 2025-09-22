package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.AttendanceDTO;
import com.hlt.usermanagement.dto.GradeDTO;
import com.hlt.usermanagement.dto.StudentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface StudentService {
    StudentDTO addStudent(StudentDTO dto);
    StudentDTO updateStudent(Long id, StudentDTO dto);
    StudentDTO getStudent(Long id);
    void removeStudent(Long id);

    StudentDTO enrollInClass(Long studentId, Long classId);

    Page<GradeDTO> getStudentGrades(Long studentId, Pageable pageable);
    Page<AttendanceDTO> getStudentAttendance(Long studentId, Pageable pageable);
}

