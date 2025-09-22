package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.AttendanceDTO;
import com.hlt.usermanagement.dto.GradeDTO;
import com.hlt.usermanagement.dto.StudentDTO;
import com.hlt.usermanagement.model.ClassModel;
import com.hlt.usermanagement.model.StudentModel;
import com.hlt.usermanagement.populator.AttendancePopulator;
import com.hlt.usermanagement.populator.GradePopulator;
import com.hlt.usermanagement.populator.StudentPopulator;
import com.hlt.usermanagement.repository.AttendanceRepository;
import com.hlt.usermanagement.repository.ClassRepository;
import com.hlt.usermanagement.repository.GradeRepository;
import com.hlt.usermanagement.repository.StudentRepository;
import com.hlt.usermanagement.services.StudentService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final GradeRepository gradeRepository;
    private final AttendanceRepository attendanceRepository;

    private final StudentPopulator studentPopulator;
    private final GradePopulator gradePopulator;
    private final AttendancePopulator attendancePopulator;

    /**
     * Add a new student
     */
    @Override
    @Transactional
    public StudentDTO addStudent(StudentDTO dto) {
        log.info("Adding new student with roll number: {}", dto.getRollNumber());

        if (studentRepository.existsByRollNumber(dto.getRollNumber())) {
            throw new HltCustomerException(ErrorCode.STUDENT_ALREADY_EXISTS);
        }

        StudentModel student = studentPopulator.toEntity(dto);
        StudentModel saved = studentRepository.save(student);

        log.info("Student created successfully with ID: {}", saved.getId());
        return studentPopulator.toDTO(saved);
    }

    /**
     * Update student details
     */
    @Override
    @Transactional
    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        log.info("Updating student with ID: {}", id);

        StudentModel student = studentRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.STUDENT_NOT_FOUND));

        studentPopulator.updateEntity(dto, student);
        StudentModel updated = studentRepository.save(student);

        log.info("Student updated successfully with ID: {}", updated.getId());
        return studentPopulator.toDTO(updated);
    }

    /**
     * Fetch student by ID
     */
    @Override
    @Transactional(readOnly = true)
    public StudentDTO getStudent(Long id) {
        log.info("Fetching student with ID: {}", id);

        StudentModel student = studentRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.STUDENT_NOT_FOUND));

        return studentPopulator.toDTO(student);
    }

    /**
     * Delete student
     */
    @Override
    @Transactional
    public void removeStudent(Long id) {
        log.info("Deleting student with ID: {}", id);

        StudentModel student = studentRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.STUDENT_NOT_FOUND));

        studentRepository.delete(student);

        log.info("Student deleted successfully with ID: {}", id);
    }

    /**
     * Enroll student into a class
     */
    @Override
    @Transactional
    public StudentDTO enrollInClass(Long studentId, Long classId) {
        log.info("Enrolling student {} into class {}", studentId, classId);

        StudentModel student = studentRepository.findById(studentId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.STUDENT_NOT_FOUND));

        ClassModel classModel = classRepository.findById(classId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.CLASS_NOT_FOUND));

        student.setClassModel(classModel);
        StudentModel updated = studentRepository.save(student);

        log.info("Student {} successfully enrolled in class {}", studentId, classId);
        return studentPopulator.toDTO(updated);
    }

    /**
     * Fetch student grades
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GradeDTO> getStudentGrades(Long studentId, Pageable pageable) {
        log.info("Fetching grades for student {} with pagination", studentId);

        if (!studentRepository.existsById(studentId)) {
            throw new HltCustomerException(ErrorCode.STUDENT_NOT_FOUND);
        }

        return gradeRepository.findByStudentId(studentId, pageable)
                .map(gradePopulator::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AttendanceDTO> getStudentAttendance(Long studentId, Pageable pageable) {
        log.info("Fetching attendance for student {} with pagination", studentId);

        if (!studentRepository.existsById(studentId)) {
            throw new HltCustomerException(ErrorCode.STUDENT_NOT_FOUND);
        }

        return attendanceRepository.findByStudentId(studentId, pageable)
                .map(attendancePopulator::toDTO);
    }

}