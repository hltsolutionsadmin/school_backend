package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.AcademicResourceAssignmentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcademicResourceAssignmentRepository extends JpaRepository<AcademicResourceAssignmentModel, Long> {

    Page<AcademicResourceAssignmentModel> findByResourceId(Long resourceId, Pageable pageable);

    Page<AcademicResourceAssignmentModel> findBySubjectId(Long subjectId, Pageable pageable);

    Optional<AcademicResourceAssignmentModel> findByResourceIdAndSubjectIdAndAcademicIdAndActiveTrue(
            Long resourceId,
            Long subjectId,
            Long academicId
    );
}
