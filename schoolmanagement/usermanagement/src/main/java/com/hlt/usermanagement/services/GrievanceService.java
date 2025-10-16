package com.hlt.usermanagement.services;


import com.hlt.usermanagement.dto.GrievanceDTO;
import com.hlt.usermanagement.dto.enums.GrievanceStatus;
import com.hlt.usermanagement.dto.enums.GrievanceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GrievanceService {

    GrievanceDTO createGrievance(GrievanceDTO dto);

    GrievanceDTO updateGrievance(Long id, GrievanceDTO dto);

    void deleteGrievance(Long id); // soft delete

    GrievanceDTO getById(Long id);

    Page<GrievanceDTO> getAll(Pageable pageable);

    Page<GrievanceDTO> getByType(GrievanceType type, Pageable pageable);

    Page<GrievanceDTO> getByStatus(GrievanceStatus status, Pageable pageable);
}

