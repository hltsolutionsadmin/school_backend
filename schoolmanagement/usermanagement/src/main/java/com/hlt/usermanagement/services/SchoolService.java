package com.hlt.usermanagement.services;

import com.hlt.usermanagement.dto.SchoolDTO;

import java.util.List;

public interface SchoolService {

    SchoolDTO saveSchool(SchoolDTO school);

    SchoolDTO getSchoolById(Long id);

    List<SchoolDTO> getAllSchools();

    SchoolDTO updateSchool(Long id, SchoolDTO schoolDTO);

    void deleteSchool(Long id);

    boolean existsByCode(String code, Long id);
}
