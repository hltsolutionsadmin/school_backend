package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.SubjectDTO;
import com.hlt.usermanagement.services.SubjectService;
import com.hlt.usermanagement.utils.SchoolAppConstants;
import com.schoolmanagement.commonservice.dto.StandardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/subjects")
@Slf4j
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    /**
     * Create a new subject
     * Only accessible by SUPER_ADMIN or ADMIN
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<StandardResponse<SubjectDTO>> addSubject(@Valid @RequestBody SubjectDTO subjectDTO) {
        log.info("Request received to create subject with name: {}", subjectDTO.getName());

        SubjectDTO created = subjectService.addSubject(subjectDTO);

        log.info("Subject created successfully with ID: {}", created.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.SUBJECT_CREATE_SUCCESS,
                created
        ));
    }

    /**
     * Update subject details
     */
    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse<SubjectDTO>> updateSubject(
            @PathVariable Long id,
            @Valid @RequestBody SubjectDTO subjectDTO) {

        log.info("Request received to update subject with ID: {}", id);

        SubjectDTO updated = subjectService.updateSubject(id, subjectDTO);

        log.info("Subject updated successfully with ID: {}", updated.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.SUBJECT_UPDATE_SUCCESS,
                updated
        ));
    }

    /**
     * Get subject by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse<SubjectDTO>> getSubjectById(@PathVariable Long id) {
        log.info("Request received to fetch subject with ID: {}", id);

        SubjectDTO subject = subjectService.getSubjectById(id);

        log.info("Subject fetched successfully with ID: {}", subject.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.SUBJECT_FETCH_SUCCESS,
                subject
        ));
    }

    /**
     * Delete subject
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse<Void>> deleteSubject(@PathVariable Long id) {
        log.info("Request received to delete subject with ID: {}", id);

        subjectService.deleteSubject(id);

        log.info("Subject deleted successfully with ID: {}", id);
        return ResponseEntity.ok(StandardResponse.message(
                SchoolAppConstants.SUBJECT_DELETE_SUCCESS
        ));
    }

    /**
     * Get all subjects for a class
     */
    @GetMapping("/class/{classId}")
    public ResponseEntity<StandardResponse<List<SubjectDTO>>> getSubjectsByClass(
            @PathVariable Long classId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("Request received to fetch subjects for class with ID: {} | page: {}, size: {}", classId, page, size);

        Pageable pageable = PageRequest.of(page, size);
        List<SubjectDTO> subjects = subjectService.getSubjectsByClass(classId, pageable).getContent();

        log.info("Fetched {} subjects for class {}", subjects.size(), classId);
        return ResponseEntity.ok(StandardResponse.list(
                SchoolAppConstants.SUBJECT_FETCH_BY_CLASS_SUCCESS,
                subjects
        ));
    }

}
