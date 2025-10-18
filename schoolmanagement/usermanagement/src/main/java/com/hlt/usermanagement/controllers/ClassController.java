package com.hlt.usermanagement.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/classes")
@Slf4j
@RequiredArgsConstructor
public class ClassController {
//
//    private final ClassService classService;
//
//    @PostMapping
//    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
//    public ResponseEntity<StandardResponse<ClassDTO>> createClass(@Valid @RequestBody ClassDTO dto) {
//        log.info("Request received to create class: {}", dto.getClassName());
//        ClassDTO savedClass = classService.createClass(dto);
//        log.info("Class created successfully with ID: {}", savedClass.getId());
//        return ResponseEntity.ok(StandardResponse.single(SchoolAppConstants.CLASS_CREATE_SUCCESS, savedClass));
//    }
//
//    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
//    public ResponseEntity<StandardResponse<ClassDTO>> updateClass(
//            @PathVariable Long id,
//            @Valid @RequestBody ClassDTO dto) {
//        log.info("Request received to update class with ID: {}", id);
//        ClassDTO updatedClass = classService.updateClass(id, dto);
//        log.info("Class updated successfully with ID: {}", updatedClass.getId());
//        return ResponseEntity.ok(StandardResponse.single(SchoolAppConstants.CLASS_UPDATE_SUCCESS, updatedClass));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<StandardResponse<ClassDTO>> getClassById(@PathVariable Long id) {
//        log.info("Request received to fetch class with ID: {}", id);
//        ClassDTO dto = classService.getClassById(id);
//        log.info("Class fetched successfully with ID: {}", dto.getId());
//        return ResponseEntity.ok(StandardResponse.single(SchoolAppConstants.CLASS_FETCH_SUCCESS, dto));
//    }
//
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
//    public ResponseEntity<StandardResponse<Void>> deleteClass(@PathVariable Long id) {
//        log.info("Request received to delete class with ID: {}", id);
//        classService.deleteClass(id);
//        log.info("Class deleted successfully with ID: {}", id);
//        return ResponseEntity.ok(StandardResponse.message(SchoolAppConstants.CLASS_DELETE_SUCCESS));
//    }
//
//    @GetMapping("/{id}/students")
//    public ResponseEntity<StandardResponse<List<StudentDTO>>> getStudentsInClass(
//            @PathVariable Long id,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        log.info("Request received to fetch students for class ID: {}", id);
//        Pageable pageable = PageRequest.of(page, size);
//        List<StudentDTO> students = classService.getStudentsInClass(id, pageable);
//        log.info("Fetched {} students for class ID: {}", students.size(), id);
//        return ResponseEntity.ok(StandardResponse.list(SchoolAppConstants.STUDENT_FETCH_SUCCESS, students));
//    }
//
//    @GetMapping("/{id}/subjects")
//    public ResponseEntity<StandardResponse<List<SubjectDTO>>> getSubjectsInClass(
//            @PathVariable Long id,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        log.info("Request received to fetch subjects for class ID: {}", id);
//        Pageable pageable = PageRequest.of(page, size);
//        List<SubjectDTO> subjects = classService.getSubjectsInClass(id, pageable);
//        log.info("Fetched {} subjects for class ID: {}", subjects.size(), id);
//        return ResponseEntity.ok(StandardResponse.list(SchoolAppConstants.SUBJECT_FETCH_SUCCESS, subjects));
//    }
}