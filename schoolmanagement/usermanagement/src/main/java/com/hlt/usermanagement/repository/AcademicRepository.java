package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.AcademicModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicRepository extends JpaRepository<AcademicModel, Long> {
    Page<AcademicModel> findByB2bUnitId(Long b2bUnitId, Pageable pageable);
}
