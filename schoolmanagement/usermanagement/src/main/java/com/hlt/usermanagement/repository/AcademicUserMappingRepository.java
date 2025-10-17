package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.AcademicModel;
import com.hlt.usermanagement.model.AcademicUserMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicUserMappingRepository extends JpaRepository<AcademicUserMapping, Long> {


    Page<AcademicUserMapping> findByUserId(Long userId, Pageable pageable);

    Page<AcademicUserMapping> findByAcademicIdAndRole(Long academicId, String role, Pageable pageable);

    void deleteByAcademicIdAndUserId(Long academicId, Long userId);

    Page<AcademicUserMapping> findByAcademic(AcademicModel academic, Pageable pageable);
}
