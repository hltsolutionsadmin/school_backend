package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.FeesSummaryDTO;

import java.util.NoSuchElementException;

public interface FeeService {

    /**
     * Retrieves and calculates the full fee summary for a student and academic year.
     * @param studentId The ID of the student.
     * @param academicYear The year (e.g., "2022-2023").
     * @return FeesSummaryDTO with all calculated details.
     * @throws NoSuchElementException if the Fee Structure is not found.
     */
    FeesSummaryDTO getFeesSummary(Long studentId, String academicYear) throws NoSuchElementException;
}
