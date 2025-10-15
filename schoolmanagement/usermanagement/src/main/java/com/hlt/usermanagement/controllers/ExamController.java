package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.ExamDto;
import com.hlt.usermanagement.services.ExamService;
import com.hlt.usermanagement.utils.SchoolAppConstants;
import com.schoolmanagement.commonservice.dto.StandardResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/exams")
@Slf4j
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    /**
     * Create a new exam
     * Only accessible by SUPER_ADMIN
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<StandardResponse<ExamDto>> createExam(@Valid @RequestBody ExamDto examDto) {
        log.info("Request received to create exam: {}", examDto.getExamName());

        ExamDto created = examService.createExam(examDto);

        log.info("Exam created successfully with ID: {}", created.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.EXAM_CREATE_SUCCESS,
                created
        ));
    }

    /**
     * Update exam
     */
    @PutMapping("/{examId}")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<StandardResponse<ExamDto>> updateExam(
            @PathVariable Long examId,
            @Valid @RequestBody ExamDto examDto) {

        log.info("Request received to update exam with ID: {}", examId);

        ExamDto updated = examService.updateExam(examId, examDto);

        log.info("Exam updated successfully with ID: {}", updated.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.EXAM_UPDATE_SUCCESS,
                updated
        ));
    }

    /**
     * Delete exam
     */
    @DeleteMapping("/{examId}")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<StandardResponse<Void>> deleteExam(@PathVariable Long examId) {
        log.info("Request received to delete exam with ID: {}", examId);

        examService.deleteExam(examId);

        log.info("Exam deleted successfully with ID: {}", examId);
        return ResponseEntity.ok(StandardResponse.message(
                SchoolAppConstants.EXAM_DELETE_SUCCESS
        ));
    }

    /**
     * Get exam by ID
     */
    @GetMapping("/{examId}")
    public ResponseEntity<StandardResponse<ExamDto>> getExamById(@PathVariable Long examId) {
        log.info("Request received to fetch exam with ID: {}", examId);

        ExamDto dto = examService.getAllExams().stream()
                .filter(e -> e.getId().equals(examId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Exam not found with ID: " + examId));

        log.info("Exam fetched successfully with ID: {}", dto.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.EXAM_FETCH_SUCCESS,
                dto
        ));
    }

    /**
     * Get all exams
     */
    @GetMapping("/all")
    public ResponseEntity<StandardResponse<List<ExamDto>>> getAllExams() {
        log.info("Request received to fetch all exams");

        List<ExamDto> exams = examService.getAllExams();

        log.info("Fetched {} exams", exams.size());
        return ResponseEntity.ok(StandardResponse.list(
                SchoolAppConstants.EXAM_FETCH_SUCCESS,
                exams
        ));
    }

    /**
     * Get exams by class ID
     */
    @GetMapping("/by-class/{classId}")
    public ResponseEntity<StandardResponse<List<ExamDto>>> getExamsByClass(@PathVariable Long classId) {
        log.info("Request received to fetch exams for class ID: {}", classId);

        List<ExamDto> exams = examService.getExamsByClassId(classId);

        log.info("Fetched {} exams for class ID: {}", exams.size(), classId);
        return ResponseEntity.ok(StandardResponse.list(
                SchoolAppConstants.EXAM_FETCH_SUCCESS,
                exams
        ));
    }

    /**
     * Get exams by subject ID
     */
    @GetMapping("/by-subject/{subjectId}")
    public ResponseEntity<StandardResponse<List<ExamDto>>> getExamsBySubject(@PathVariable Long subjectId) {
        log.info("Request received to fetch exams for subject ID: {}", subjectId);

        List<ExamDto> exams = examService.getExamsBySubjectId(subjectId);

        log.info("Fetched {} exams for subject ID: {}", exams.size(), subjectId);
        return ResponseEntity.ok(StandardResponse.list(
                SchoolAppConstants.EXAM_FETCH_SUCCESS,
                exams
        ));
    }

    /**
     * Get exams by exam type ID
     */
    @GetMapping("/by-type/{examTypeId}")
    public ResponseEntity<StandardResponse<List<ExamDto>>> getExamsByExamType(@PathVariable Long examTypeId) {
        log.info("Request received to fetch exams for exam type ID: {}", examTypeId);

        List<ExamDto> exams = examService.getExamsByExamTypeId(examTypeId);

        log.info("Fetched {} exams for exam type ID: {}", exams.size(), examTypeId);
        return ResponseEntity.ok(StandardResponse.list(
                SchoolAppConstants.EXAM_FETCH_SUCCESS,
                exams
        ));
    }
}

