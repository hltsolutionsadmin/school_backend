package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.ExamTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for ExamTypeModel.
 * Provides CRUD operations for exam types.
 */
@Repository
public interface ExamTypeRepository extends JpaRepository<ExamTypeModel, Long> {

    /**
     * Optional: Finds an exam type by its name.
     *
     * @param typeName the name of the exam type
     * @return the ExamTypeModel with the given name, or null if not found
     */
    ExamTypeModel findByTypeName(String typeName);
}
