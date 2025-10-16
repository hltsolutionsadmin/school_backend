package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.GrievanceDTO;
import com.hlt.usermanagement.dto.enums.GrievanceStatus;
import com.hlt.usermanagement.dto.enums.GrievanceType;
import com.hlt.usermanagement.services.GrievanceService;
import com.hlt.usermanagement.utils.SchoolAppConstants;
import com.schoolmanagement.commonservice.dto.StandardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/v1/grievances")
@Slf4j
@RequiredArgsConstructor
public class GrievanceController {

    private final GrievanceService grievanceService;

    /**
     * Create a new grievance
     * Accessible by all authenticated users
     */
    @PostMapping("/create")
    public ResponseEntity<StandardResponse<GrievanceDTO>> createGrievance(@Valid @RequestBody GrievanceDTO grievanceDTO) {
        log.info("Request received to create grievance of type: {}", grievanceDTO.getType());

        GrievanceDTO created = grievanceService.createGrievance(grievanceDTO);

        log.info("Grievance created successfully with ID: {}", created.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.GRIEVANCE_CREATE_SUCCESS,
                created
        ));
    }

    /**
     * Update grievance details
     */
    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse<GrievanceDTO>> updateGrievance(
            @PathVariable Long id,
            @Valid @RequestBody GrievanceDTO grievanceDTO) {

        log.info("Request received to update grievance with ID: {}", id);

        GrievanceDTO updated = grievanceService.updateGrievance(id, grievanceDTO);

        log.info("Grievance updated successfully with ID: {}", updated.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.GRIEVANCE_UPDATE_SUCCESS,
                updated
        ));
    }

    /**
     * Get grievance by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse<GrievanceDTO>> getGrievanceById(@PathVariable Long id) {
        log.info("Request received to fetch grievance with ID: {}", id);

        GrievanceDTO grievance = grievanceService.getById(id);

        log.info("Grievance fetched successfully with ID: {}", grievance.getId());
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.GRIEVANCE_FETCH_SUCCESS,
                grievance
        ));
    }

    /**
     * Delete (soft delete) grievance
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse<Void>> deleteGrievance(@PathVariable Long id) {
        log.info("Request received to delete grievance with ID: {}", id);

        grievanceService.deleteGrievance(id);

        log.info("Grievance deleted successfully with ID: {}", id);
        return ResponseEntity.ok(StandardResponse.message(
                SchoolAppConstants.GRIEVANCE_DELETE_SUCCESS
        ));
    }

    /**
     * Get all grievances (paginated)
     */
    @GetMapping
    public ResponseEntity<StandardResponse<List<GrievanceDTO>>> getAllGrievances(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("Request received to fetch grievances | page: {}, size: {}", page, size);

        Pageable pageable = PageRequest.of(page, size);
        Page<GrievanceDTO> grievances = grievanceService.getAll(pageable);

        log.info("Fetched {} grievances", grievances.getContent().size());
        return ResponseEntity.ok(StandardResponse.list(
                SchoolAppConstants.GRIEVANCE_FETCH_ALL_SUCCESS,
                grievances.getContent()
        ));
    }

    /**
     * Get grievances by type (Complaint, Concern, Request)
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<StandardResponse<List<GrievanceDTO>>> getGrievancesByType(
            @PathVariable GrievanceType type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("Request received to fetch grievances by type: {} | page: {}, size: {}", type, page, size);

        Pageable pageable = PageRequest.of(page, size);
        Page<GrievanceDTO> grievances = grievanceService.getByType(type, pageable);

        log.info("Fetched {} grievances of type {}", grievances.getContent().size(), type);
        return ResponseEntity.ok(StandardResponse.list(
                SchoolAppConstants.GRIEVANCE_FETCH_BY_TYPE_SUCCESS,
                grievances.getContent()
        ));
    }

    /**
     * Get grievances by status (Raised, Solved, Declined)
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<StandardResponse<List<GrievanceDTO>>> getGrievancesByStatus(
            @PathVariable GrievanceStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("Request received to fetch grievances by status: {} | page: {}, size: {}", status, page, size);

        Pageable pageable = PageRequest.of(page, size);
        Page<GrievanceDTO> grievances = grievanceService.getByStatus(status, pageable);

        log.info("Fetched {} grievances with status {}", grievances.getContent().size(), status);
        return ResponseEntity.ok(StandardResponse.list(
                SchoolAppConstants.GRIEVANCE_FETCH_BY_STATUS_SUCCESS,
                grievances.getContent()
        ));
    }

    /**
     * Get list of available grievance types
     */
    @GetMapping("/types")
    public ResponseEntity<StandardResponse<GrievanceType[]>> getGrievanceTypes() {
        log.info("Request received to fetch grievance types");

        GrievanceType[] types = GrievanceType.values();

        log.info("Fetched {} grievance types", types.length);
        return ResponseEntity.ok(StandardResponse.single(
                SchoolAppConstants.GRIEVANCE_TYPES_FETCH_SUCCESS,
                types
        ));
    }
}


