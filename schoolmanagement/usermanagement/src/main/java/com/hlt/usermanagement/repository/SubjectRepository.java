package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.SubjectModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<SubjectModel, Long> {
    Page<SubjectModel> findByB2bUnit_Id(Long b2bUnitId, Pageable pageable);
}
