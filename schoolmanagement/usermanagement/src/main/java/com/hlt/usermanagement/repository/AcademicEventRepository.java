package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.AcademicEventModel;
import com.hlt.usermanagement.model.AcademicModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicEventRepository extends JpaRepository<AcademicEventModel, Long> {
    List<AcademicEventModel> findByAcademicUnit(AcademicModel academicUnit);
}
