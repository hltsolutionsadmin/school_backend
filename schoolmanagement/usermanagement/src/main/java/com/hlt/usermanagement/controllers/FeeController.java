package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.FeeDTO;
import com.hlt.usermanagement.services.FeeService;
import com.schoolmanagement.commonservice.dto.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/fees")
@RequiredArgsConstructor
public class FeeController {

    private final FeeService feeService;

    private static final String FEE_CREATED = "Fee created successfully";
    private static final String FEE_UPDATED = "Fee updated successfully";
    private static final String FEE_RETRIEVED = "Fee retrieved successfully";
    private static final String FEE_DELETED = "Fee deleted successfully";
    private static final String FEES_RETRIEVED_ACADEMIC = "Fees retrieved for academic";
    private static final String FEES_RETRIEVED_STUDENT = "Fees retrieved for student";

    @PostMapping
    public StandardResponse<FeeDTO> createFee(@Valid @RequestBody FeeDTO dto) {
        FeeDTO created = feeService.createFee(dto);
        return StandardResponse.single(FEE_CREATED, created);
    }

    @PutMapping("/{id}")
    public StandardResponse<FeeDTO> updateFee(@PathVariable Long id, @Valid @RequestBody FeeDTO dto) {
        FeeDTO updated = feeService.updateFee(id, dto);
        return StandardResponse.single(FEE_UPDATED, updated);
    }

    @GetMapping("/{id}")
    public StandardResponse<FeeDTO> getFeeById(@PathVariable Long id) {
        FeeDTO fee = feeService.getFeeById(id);
        return StandardResponse.single(FEE_RETRIEVED, fee);
    }

    @DeleteMapping("/{id}")
    public StandardResponse<Void> deleteFee(@PathVariable Long id) {
        feeService.deleteFee(id);
        return StandardResponse.message(FEE_DELETED);
    }

    @GetMapping("/academic/{academicId}")
    public StandardResponse<Page<FeeDTO>> getFeesByAcademic(
            @PathVariable Long academicId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<FeeDTO> fees = feeService.getFeesByAcademic(academicId, pageable);
        return StandardResponse.page(FEES_RETRIEVED_ACADEMIC, fees);
    }

    @GetMapping("/student/{studentId}")
    public StandardResponse<Page<FeeDTO>> getFeesByStudent(
            @PathVariable Long studentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<FeeDTO> fees = feeService.getFeesByStudent(studentId, pageable);
        return StandardResponse.page(FEES_RETRIEVED_STUDENT, fees);
    }
}
