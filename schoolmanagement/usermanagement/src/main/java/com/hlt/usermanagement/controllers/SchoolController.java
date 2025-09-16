package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.SchoolDTO;
import com.hlt.usermanagement.services.SchoolService;
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
@RequestMapping("/v1/schools")
@Slf4j
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;


    /**
     * API for creating a new school.
     * Only accessible by ADMIN role.
     */
    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<StandardResponse<SchoolDTO>> createSchool(
            @Valid @RequestBody SchoolDTO schoolDTO) {
        log.info("Request received to create school: {}", schoolDTO.getName());

        SchoolDTO savedSchoolDTO = schoolService.saveSchool(schoolDTO);

        log.info("School created successfully with ID: {}", savedSchoolDTO.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.SCHOOL_CREATE_SUCCESS,
                savedSchoolDTO
        ));
    }

    /**
     * Get school by ID
     */
    @GetMapping("/{schoolId}")
    public ResponseEntity<StandardResponse<SchoolDTO>> getSchoolById(@PathVariable Long schoolId) {
        log.info("Request received to fetch school with ID: {}", schoolId);

        SchoolDTO dto = schoolService.getSchoolById(schoolId);

        log.info("School fetched successfully with ID: {}", dto.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.SCHOOL_FETCH_SUCCESS,
                dto
        ));
    }

    /**
     * Get all schools
     */
    @GetMapping("/all")
    public ResponseEntity<StandardResponse<List<SchoolDTO>>> getAllSchools() {
        log.info("Request received to fetch all schools");

        List<SchoolDTO> schools = schoolService.getAllSchools();

        log.info("Fetched {} schools", schools.size());
        return ResponseEntity.ok(StandardResponse.list(
                SchoolAppConstants.SCHOOL_FETCH_SUCCESS,
                schools
        ));
    }

    /**
     * Update school details
     */
    @PutMapping("/{schoolId}")
    public ResponseEntity<StandardResponse<SchoolDTO>> updateSchool(
            @PathVariable Long schoolId,
            @Valid @RequestBody SchoolDTO schoolDTO) {

        log.info("Request received to update school with ID: {}", schoolId);

        SchoolDTO updatedSchoolDTO = schoolService.updateSchool(schoolId, schoolDTO);

        log.info("School updated successfully with ID: {}", updatedSchoolDTO.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.SCHOOL_UPDATE_SUCCESS,
                updatedSchoolDTO
        ));
    }

    /**
     * Delete a school
     */
    @DeleteMapping("/{schoolId}")
    public ResponseEntity<StandardResponse<Void>> deleteSchool(@PathVariable Long schoolId) {
        log.info("Request received to delete school with ID: {}", schoolId);

        schoolService.deleteSchool(schoolId);

        log.info("School deleted successfully with ID: {}", schoolId);
        return ResponseEntity.ok(StandardResponse.message(
                SchoolAppConstants.SCHOOL_DELETE_SUCCESS
        ));
    }
}
