package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.FeeStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FeeStructureRepository extends JpaRepository<FeeStructure, Long> {

    /**
     * Finds the specific fee structure for a student and academic year.
     * @param studentId The ID of the student.
     * @param academicYear The year (e.g., "2022-2023").
     * @return Optional containing the FeeStructure if found.
     */
    Optional<FeeStructure> findByStudentIdAndAcademicYear(Long studentId, String academicYear);
}
