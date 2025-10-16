package com.hlt.usermanagement.repository;


import com.hlt.usermanagement.dto.enums.GrievanceStatus;
import com.hlt.usermanagement.dto.enums.GrievanceType;
import com.hlt.usermanagement.model.GrievanceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrievanceRepository extends JpaRepository<GrievanceModel, Long> {
    Page<GrievanceModel> findByTypeAndActiveTrue(GrievanceType type, Pageable pageable);
    Page<GrievanceModel> findByStatusAndActiveTrue(GrievanceStatus status, Pageable pageable);
    Page<GrievanceModel> findByActiveTrue(Pageable pageable);
    boolean existsByTitleAndActiveTrue(String title);

}

