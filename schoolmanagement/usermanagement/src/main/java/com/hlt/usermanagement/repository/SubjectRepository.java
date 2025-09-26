package com.hlt.usermanagement.repository;

import com.hlt.usermanagement.model.SubjectModel;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectModel, Long> {

    Page<SubjectModel> findByClassModelId(Long classId, Pageable pageable);

    Optional<SubjectModel> findByCode(String code);

    boolean existsByNameAndClassModelId(String name, Long classId);

    boolean existsByCodeAndClassModelId(String code, Long classId);
}
