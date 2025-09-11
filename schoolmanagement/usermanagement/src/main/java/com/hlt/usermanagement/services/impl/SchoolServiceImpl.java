package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.SchoolDTO;
import com.hlt.usermanagement.model.AddressModel;
import com.hlt.usermanagement.model.RoleModel;
import com.hlt.usermanagement.model.SchoolModel;
import com.hlt.usermanagement.model.UserModel;
import com.hlt.usermanagement.populator.SchoolPopulator;
import com.hlt.usermanagement.repository.RoleRepository;
import com.hlt.usermanagement.repository.SchoolRepository;
import com.hlt.usermanagement.repository.UserRepository;
import com.hlt.usermanagement.services.SchoolService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import com.schoolmanagement.commonservice.enums.ERole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;
    private final SchoolPopulator schoolPopulator;
    private final RoleRepository eRoleRepository;

    @Override
    @Transactional
    public SchoolDTO saveSchool(SchoolDTO schoolDTO) {

        if (schoolRepository.existsByCode(schoolDTO.getCode())) {
            throw new HltCustomerException(ErrorCode.SCHOOL_ALREADY_EXISTS);
        }
        SchoolModel school = new SchoolModel();

        if (schoolDTO.getAdminId() != null) {
            UserModel principal = userRepository.findById(schoolDTO.getAdminId())
                    .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND));
            principal.setSchool(school);
            RoleModel adminRole = eRoleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new HltCustomerException(ErrorCode.ROLE_NOT_FOUND));
            principal.setRoles(Set.of(adminRole));
            school.setAdmin(principal);
        }
        mapSchoolDtoToModel(schoolDTO, school);
        SchoolModel savedSchool = schoolRepository.save(school);
        return schoolPopulator.toDTO(savedSchool);
    }

    private void mapSchoolDtoToModel(SchoolDTO dto, SchoolModel model) {
        model.setName(dto.getName());
        model.setCode(dto.getCode());
        model.setContactNumber(dto.getContactNumber());
        model.setEmail(dto.getEmail());

        if (dto.getAddress() != null) {
            AddressModel address = new AddressModel();
            address.setStreet(dto.getAddress().getStreet());
            address.setCity(dto.getAddress().getCity());
            address.setState(dto.getAddress().getState());
            address.setCountry(dto.getAddress().getCountry());
            address.setPostalCode(dto.getAddress().getPostalCode());
            model.setAddress(address);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public SchoolDTO getSchoolById(Long id) {
        SchoolModel school = schoolRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.SCHOOL_NOT_FOUND));
        return schoolPopulator.toDTO(school);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SchoolDTO> getAllSchools() {
        return schoolRepository.findAll()
                .stream()
                .map(schoolPopulator::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public SchoolDTO updateSchool(Long id, SchoolDTO schoolDTO) {
        SchoolModel school = schoolRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.SCHOOL_NOT_FOUND));

        if (StringUtils.hasText(schoolDTO.getName())) {
            school.setName(schoolDTO.getName());
        }

        if (StringUtils.hasText(schoolDTO.getCode()) && !schoolDTO.getCode().equals(school.getCode())) {
            if (existsByCode(schoolDTO.getCode(), id)) {
                throw new HltCustomerException(ErrorCode.SCHOOL_CODE_ALREADY_IN_USE);
            }
            school.setCode(schoolDTO.getCode());
        }

        if (StringUtils.hasText(schoolDTO.getContactNumber())) {
            school.setContactNumber(schoolDTO.getContactNumber());
        }

        if (StringUtils.hasText(schoolDTO.getEmail())) {
            school.setEmail(schoolDTO.getEmail());
        }

        schoolRepository.save(school);

        return schoolPopulator.toDTO(school);
    }


    @Override
    @Transactional
    public void deleteSchool(Long id) {
        SchoolModel school = schoolRepository.findById(id)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.SCHOOL_NOT_FOUND));
        schoolRepository.delete(school);
    }

    @Override
    public boolean existsByCode(String code, Long id) {
        if (id == null) {
            return schoolRepository.existsByCode(code);
        } else {
            return schoolRepository.existsByCodeAndIdNot(code, id);
        }
    }
}
