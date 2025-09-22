package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.GradeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GradeRepository extends JpaRepository<GradeModel, Long> {
    Page<GradeModel> findByStudentId(Long studentId, Pageable pageable);
}
