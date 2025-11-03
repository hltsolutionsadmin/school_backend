package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.AcademicDTO;
import com.hlt.usermanagement.dto.AcademicUserDTO;
import com.hlt.usermanagement.dto.AssignUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AcademicService {

    AcademicDTO createAcademic(AcademicDTO dto);

    Page<AcademicDTO> getAllAcademics(Long b2bUnitId, Pageable pageable);

    void assignUsers(Long academicId, AssignUserDTO dto);

   Page<AcademicUserDTO> getUsersInAcademic(Long academicId, Pageable pageable);

    Page<AcademicDTO> getAcademicsByUserId(Long userId, int page, int size);

}
