package com.hlt.usermanagement.controllers;

import com.hlt.usermanagement.dto.FeesSummaryDTO;
import com.hlt.usermanagement.services.FeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/fees")
public class FeeController {

    private final FeeService feeService;

    public FeeController(FeeService feeService) {
        this.feeService = feeService;
    }

    /**
     * Endpoint to fet
     * ch the student's fee summary and details for a specific academic year.
     * URL Example: GET /api/fees/summary/1?year=2022-2023
     * * We use studentId=1 for testing with the data loaded by the TestDataLoader.
     */
    @GetMapping("/summary/{studentId}")
    public ResponseEntity<FeesSummaryDTO> getFeesSummary(
            @PathVariable Long studentId,
            @RequestParam(required = true) String year) {

        try {
            FeesSummaryDTO summary = feeService.getFeesSummary(studentId, year);
            return ResponseEntity.ok(summary);
        } catch (NoSuchElementException e) {
            // Return a 404 Not Found if the resource is missing
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    /**
     * Placeholder for the receipt download endpoint.
     */
    @GetMapping("/receipt/{transactionRef}")
    public ResponseEntity<String> downloadReceipt(@PathVariable String transactionRef) {
        // In a real application, this would fetch a PDF/File and set Content-Type header
        return ResponseEntity.ok("Receipt download for transaction reference: " + transactionRef);
    }
}

