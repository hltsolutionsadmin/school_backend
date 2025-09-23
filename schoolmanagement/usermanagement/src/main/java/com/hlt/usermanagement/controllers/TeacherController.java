package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.ClassDTO;
import com.hlt.usermanagement.dto.SubjectDTO;
import com.hlt.usermanagement.dto.TeacherDTO;
import com.hlt.usermanagement.services.TeacherService;
import com.hlt.usermanagement.utils.SchoolAppConstants;
import com.schoolmanagement.commonservice.dto.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/teachers")
@Slf4j
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;


    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<StandardResponse<TeacherDTO>> createTeacher(@Valid @RequestBody TeacherDTO dto) {
        log.info("Request received to create teacher with userId: {}", dto.getUserId());
        TeacherDTO createdTeacher = teacherService.createTeacher(dto);
        log.info("Teacher created successfully with ID: {}", createdTeacher.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.TEACHER_CREATE_SUCCESS,
                createdTeacher
        ));
    }


    @PutMapping("/{teacherId}")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<StandardResponse<TeacherDTO>> updateTeacher(
            @PathVariable Long teacherId,
            @Valid @RequestBody TeacherDTO dto) {
        log.info("Request received to update teacher with ID: {}", teacherId);
        TeacherDTO updatedTeacher = teacherService.updateTeacher(teacherId, dto);
        log.info("Teacher updated successfully with ID: {}", updatedTeacher.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.TEACHER_UPDATE_SUCCESS,
                updatedTeacher
        ));
    }


    @GetMapping("/{teacherId}")
    public ResponseEntity<StandardResponse<TeacherDTO>> getTeacher(@PathVariable Long teacherId) {
        log.info("Request received to fetch teacher with ID: {}", teacherId);
        TeacherDTO dto = teacherService.getTeacherById(teacherId);
        log.info("Teacher fetched successfully with ID: {}", dto.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.TEACHER_FETCH_SUCCESS,
                dto
        ));
    }


    @DeleteMapping("/{teacherId}")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<StandardResponse<Void>> deleteTeacher(@PathVariable Long teacherId) {
        log.info("Request received to delete teacher with ID: {}", teacherId);
        teacherService.deleteTeacher(teacherId);
        log.info("Teacher deleted successfully with ID: {}", teacherId);
        return ResponseEntity.ok(StandardResponse.message(SchoolAppConstants.TEACHER_DELETE_SUCCESS));
    }

    @GetMapping("/{teacherId}/classes")
    public ResponseEntity<StandardResponse<Page<ClassDTO>>> getTeacherClasses(
            @PathVariable Long teacherId,
            @PageableDefault(size = 10) Pageable pageable) {
        log.info("Request received to fetch classes for teacher ID: {}", teacherId);
        Page<ClassDTO> classes = teacherService.getClassesAssignedToTeacher(teacherId, pageable);
        log.info("Fetched {} classes for teacher ID: {}", classes.getTotalElements(), teacherId);
        return ResponseEntity.ok(StandardResponse.page(
                SchoolAppConstants.TEACHER_CLASSES_FETCH_SUCCESS,
                classes
        ));
    }

    @GetMapping("/{teacherId}/subjects")
    public ResponseEntity<StandardResponse<Page<SubjectDTO>>> getTeacherSubjects(
            @PathVariable Long teacherId,
            @PageableDefault(size = 10) Pageable pageable) {
        log.info("Request received to fetch subjects for teacher ID: {}", teacherId);
        Page<SubjectDTO> subjects = teacherService.getSubjectsTaughtByTeacher(teacherId, pageable);
        log.info("Fetched {} subjects for teacher ID: {}", subjects.getTotalElements(), teacherId);
        return ResponseEntity.ok(StandardResponse.page(
                SchoolAppConstants.TEACHER_SUBJECTS_FETCH_SUCCESS,
                subjects
        ));
    }
}
