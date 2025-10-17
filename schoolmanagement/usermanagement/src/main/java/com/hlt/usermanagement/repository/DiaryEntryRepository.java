package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.AcademicModel;
import com.hlt.usermanagement.model.DiaryEntryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryEntryRepository extends JpaRepository<DiaryEntryModel, Long> {

    Page<DiaryEntryModel> findByAcademic(AcademicModel academic, Pageable pageable);

    Page<DiaryEntryModel> findByAcademicAndActiveTrue(AcademicModel academic, Pageable pageable);
}
