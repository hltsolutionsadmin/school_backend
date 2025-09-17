package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.SubjectModel;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectModel, Long> {
    Page<SubjectModel> findByClassModelId(Long classId, Pageable pageable);
}
