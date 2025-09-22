package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.AttendanceDTO;
import com.hlt.usermanagement.dto.GradeDTO;
import com.hlt.usermanagement.dto.StudentDTO;
import com.hlt.usermanagement.services.StudentService;
import com.hlt.usermanagement.utils.SchoolAppConstants;
import com.schoolmanagement.commonservice.dto.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/v1/students")
@Slf4j
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    /**
     * Create a new student
     * Only accessible by SUPER_ADMIN or ADMIN
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<StandardResponse<StudentDTO>> createStudent(
            @Valid @RequestBody StudentDTO studentDTO) {
        log.info("Request received to create student with roll number: {}", studentDTO.getRollNumber());

        StudentDTO savedStudent = studentService.addStudent(studentDTO);

        log.info("Student created successfully with ID: {}", savedStudent.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.STUDENT_CREATE_SUCCESS,
                savedStudent
        ));
    }

    /**
     * Get student by ID
     */
    @GetMapping("/{studentId}")
    public ResponseEntity<StandardResponse<StudentDTO>> getStudentById(@PathVariable Long studentId) {
        log.info("Request received to fetch student with ID: {}", studentId);

        StudentDTO student = studentService.getStudent(studentId);

        log.info("Student fetched successfully with ID: {}", student.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.STUDENT_FETCH_SUCCESS,
                student
        ));
    }

    /**
     * Update student details
     */
    @PutMapping("/{studentId}")
    public ResponseEntity<StandardResponse<StudentDTO>> updateStudent(
            @PathVariable Long studentId,
            @Valid @RequestBody StudentDTO studentDTO) {

        log.info("Request received to update student with ID: {}", studentId);

        StudentDTO updatedStudent = studentService.updateStudent(studentId, studentDTO);

        log.info("Student updated successfully with ID: {}", updatedStudent.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.STUDENT_UPDATE_SUCCESS,
                updatedStudent
        ));
    }

    /**
     * Delete student
     */
    @DeleteMapping("/{studentId}")
    public ResponseEntity<StandardResponse<Void>> deleteStudent(@PathVariable Long studentId) {
        log.info("Request received to delete student with ID: {}", studentId);

        studentService.removeStudent(studentId);

        log.info("Student deleted successfully with ID: {}", studentId);
        return ResponseEntity.ok(StandardResponse.message(
                SchoolAppConstants.STUDENT_DELETE_SUCCESS
        ));
    }

    /**
     * Enroll student into a class
     */
    @PostMapping("/{studentId}/enroll/{classId}")
    public ResponseEntity<StandardResponse<StudentDTO>> enrollStudentInClass(
            @PathVariable Long studentId,
            @PathVariable Long classId) {

        log.info("Request received to enroll student {} into class {}", studentId, classId);

        StudentDTO enrolledStudent = studentService.enrollInClass(studentId, classId);

        log.info("Student {} successfully enrolled in class {}", studentId, classId);
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.STUDENT_ENROLL_SUCCESS,
                enrolledStudent
        ));
    }

    /**
     * Get student grades
     */
    /**
     * Get student grades with pagination
     */
    /**
     * Get student grades with pagination
     */
    @GetMapping("/{studentId}/grades")
    public ResponseEntity<StandardResponse<List<GradeDTO>>> getStudentGrades(
            @PathVariable Long studentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("Request received to fetch grades for student {} with pagination", studentId);

        Pageable pageable = PageRequest.of(page, size);
        List<GradeDTO> grades = studentService.getStudentGrades(studentId, pageable).getContent();

        log.info("Fetched {} grades for student {}", grades.size(), studentId);
        return ResponseEntity.ok(StandardResponse.list(
                SchoolAppConstants.STUDENT_FETCH_GRADES_SUCCESS,
                grades
        ));
    }

    /**
     * Get student attendance with pagination
     */
    @GetMapping("/{studentId}/attendance")
    public ResponseEntity<StandardResponse<List<AttendanceDTO>>> getStudentAttendance(
            @PathVariable Long studentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("Request received to fetch attendance for student {} with pagination", studentId);

        Pageable pageable = PageRequest.of(page, size);
        List<AttendanceDTO> attendance = studentService.getStudentAttendance(studentId, pageable).getContent();

        log.info("Fetched {} attendance records for student {}", attendance.size(), studentId);
        return ResponseEntity.ok(StandardResponse.list(
                SchoolAppConstants.STUDENT_FETCH_ATTENDANCE_SUCCESS,
                attendance
        ));
    }


}
