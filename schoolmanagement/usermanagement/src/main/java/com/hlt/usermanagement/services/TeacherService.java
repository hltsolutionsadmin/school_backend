package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.ClassDTO;
import com.hlt.usermanagement.dto.SubjectDTO;
import com.hlt.usermanagement.dto.TeacherDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {

    TeacherDTO createTeacher(TeacherDTO dto);

    TeacherDTO updateTeacher(Long teacherId, TeacherDTO dto);

    TeacherDTO getTeacherById(Long teacherId);

    void deleteTeacher(Long teacherId);

    Page<ClassDTO> getClassesAssignedToTeacher(Long teacherId, Pageable pageable);

    Page<SubjectDTO> getSubjectsTaughtByTeacher(Long teacherId, Pageable pageable);
}
