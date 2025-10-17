package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.FeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeeService {

    FeeDTO createFee(FeeDTO dto);

    FeeDTO updateFee(Long id, FeeDTO dto);

    FeeDTO getFeeById(Long id);

    void deleteFee(Long id);

    Page<FeeDTO> getFeesByAcademic(Long academicId, Pageable pageable);

    Page<FeeDTO> getFeesByStudent(Long studentId, Pageable pageable);
}
