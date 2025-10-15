package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.HomeworkDTO;
import com.hlt.usermanagement.services.HomeworkService;
import com.hlt.usermanagement.utils.SchoolAppConstants;
import com.schoolmanagement.commonservice.dto.StandardResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/homework")
@Slf4j
@RequiredArgsConstructor
public class HomeworkController {

//    private final HomeworkService homeworkService;
//
//    /**
//     * Create a new homework.
//     */
//    @PostMapping("/create")
//    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_SUPER_ADMIN')")
//    public ResponseEntity<StandardResponse<HomeworkDTO>> createHomework(@Valid @RequestBody HomeworkDTO dto) {
//        HomeworkDTO created = homeworkService.createHomework(dto);
//        log.info("Homework created successfully with ID: {}", created.getId());
//        return ResponseEntity.ok(StandardResponse.single(
//                SchoolAppConstants.HOMEWORK_CREATE_SUCCESS,
//                created
//        ));
//    }
//
//    /**
//     * Fetch homework by ID.
//     */
//    @GetMapping("/{homeworkId}")
//    public ResponseEntity<StandardResponse<HomeworkDTO>> getHomeworkById(@PathVariable Long homeworkId) {
//        log.info("Request received to fetch homework with ID: {}", homeworkId);
//        HomeworkDTO dto = homeworkService.getHomeworkById(homeworkId);
//        log.info("Homework fetched successfully with ID: {}", dto.getId());
//        return ResponseEntity.ok(StandardResponse.single(
//                SchoolAppConstants.HOMEWORK_FETCH_SUCCESS,
//                dto
//        ));
//    }
//
//    /**
//     * Fetch paginated homework for a student.
//     */
//    @GetMapping("/student/{studentId}")
//    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_SUPER_ADMIN')")
//    public ResponseEntity<StandardResponse<Page<HomeworkDTO>>> getHomeworkByStudent(
//            @PathVariable Long studentId,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "assignDate") String sortBy,
//            @RequestParam(defaultValue = "desc") String sortDir
//    ) {
//        log.info("Request received to fetch homework for student ID: {}", studentId);
//        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//        Pageable pageable = PageRequest.of(page, size, sort);
//        Page<HomeworkDTO> homeworkPage = homeworkService.getHomeworkByStudent(studentId, pageable);
//        log.info("Fetched {} homework entries for student ID: {}", homeworkPage.getTotalElements(), studentId);
//        return ResponseEntity.ok(StandardResponse.page(
//                SchoolAppConstants.HOMEWORK_LIST_FETCH_SUCCESS,
//                homeworkPage
//        ));
//    }
//
//    /**
//     * Fetch paginated homework for a staff.
//     */
//    @GetMapping("/staff/{staffId}")
//    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_SUPER_ADMIN')")
//    public ResponseEntity<StandardResponse<Page<HomeworkDTO>>> getHomeworkByStaff(
//            @PathVariable Long staffId,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "assignDate") String sortBy,
//            @RequestParam(defaultValue = "desc") String sortDir
//    ) {
//        log.info("Request received to fetch homework for staff ID: {}", staffId);
//        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
//        Pageable pageable = PageRequest.of(page, size, sort);
//        Page<HomeworkDTO> homeworkPage = homeworkService.getHomeworkByStaff(staffId, pageable);
//        log.info("Fetched {} homework entries for staff ID: {}", homeworkPage.getTotalElements(), staffId);
//        return ResponseEntity.ok(StandardResponse.page(
//                SchoolAppConstants.HOMEWORK_LIST_FETCH_SUCCESS,
//                homeworkPage
//        ));
//    }
//
//    /**
//     * Delete a homework.
//     */
//    @DeleteMapping("/{homeworkId}")
//    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
//    public ResponseEntity<StandardResponse<Void>> deleteHomework(@PathVariable Long homeworkId) {
//        log.info("Request received to delete homework with ID: {}", homeworkId);
//        homeworkService.deleteHomework(homeworkId);
//        log.info("Homework deleted successfully with ID: {}", homeworkId);
//        return ResponseEntity.ok(StandardResponse.message(SchoolAppConstants.HOMEWORK_DELETE_SUCCESS));
//    }
//
//    /**
//     * Update a homework.
//     */
//    @PutMapping("/{homeworkId}")
//    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_SUPER_ADMIN')")
//    public ResponseEntity<StandardResponse<HomeworkDTO>> updateHomework(
//            @PathVariable Long homeworkId,
//            @Valid @RequestBody HomeworkDTO dto) {
//        log.info("Request received to update homework with ID: {}", homeworkId);
//        HomeworkDTO updated = homeworkService.updateHomework(homeworkId, dto);
//        log.info("Homework updated successfully with ID: {}", updated.getId());
//        return ResponseEntity.ok(StandardResponse.single(
//                SchoolAppConstants.HOMEWORK_UPDATE_SUCCESS,
//                updated
//        ));
//    }
//
//
//    /**
//     * Fetch all homework for a staff (list).
//     */
//    @GetMapping("/staff/{staffId}/list")
//    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_SUPER_ADMIN')")
//    public ResponseEntity<StandardResponse<List<HomeworkDTO>>> getHomeworkByStaffList(@PathVariable Long staffId) {
//        log.info("Request received to fetch all homework for staff ID: {}", staffId);
//        List<HomeworkDTO> homeworkList = homeworkService.getHomeworkByStaff(staffId);
//        log.info("Fetched {} homework entries for staff ID: {}", homeworkList.size(), staffId);
//        return ResponseEntity.ok(StandardResponse.list(
//                SchoolAppConstants.HOMEWORK_LIST_FETCH_SUCCESS,
//                homeworkList
//        ));
//    }

}
