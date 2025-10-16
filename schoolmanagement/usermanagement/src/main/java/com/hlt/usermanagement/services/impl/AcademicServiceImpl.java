package com.hlt.usermanagement.services.impl;

import com.hlt.usermanagement.dto.AcademicDTO;
import com.hlt.usermanagement.dto.AssignUserDTO;

import com.hlt.usermanagement.model.AcademicModel;
import com.hlt.usermanagement.model.AcademicUserMapping;
import com.hlt.usermanagement.model.B2BUnitModel;
import com.hlt.usermanagement.model.UserModel;
import com.hlt.usermanagement.populator.AcademicPopulator;
import com.hlt.usermanagement.repository.AcademicRepository;
import com.hlt.usermanagement.repository.AcademicUserMappingRepository;
import com.hlt.usermanagement.repository.UserRepository;
import com.hlt.usermanagement.services.AcademicService;
import com.schoolmanagement.auth.exception.handling.ErrorCode;
import com.schoolmanagement.auth.exception.handling.HltCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AcademicServiceImpl implements AcademicService {

    private final AcademicRepository academicRepository;
    private final AcademicUserMappingRepository mappingRepository;
    private final UserRepository userRepository;
    private final AcademicPopulator academicPopulator;

    @Override
    public AcademicDTO createAcademic(AcademicDTO dto) {
        AcademicModel model = new AcademicModel();

        model.setName(dto.getName());
        model.setCode(dto.getCode());
        model.setDescription(dto.getDescription());
        model.setActive(dto.getActive() != null ? dto.getActive() : true);
        model.setSortOrder(dto.getSortOrder());
        model.setSection(dto.getSection());
        model.setCapacity(dto.getCapacity());
        model.setStartDate(dto.getStartDate());
        model.setEndDate(dto.getEndDate());
        model.setSubjectCount(dto.getSubjectCount());
        model.setTeacherCount(dto.getTeacherCount());
        model.setNotes(dto.getNotes());

        if (dto.getB2bUnitId() != null) {
            B2BUnitModel b2bUnit = new B2BUnitModel();
            b2bUnit.setId(dto.getB2bUnitId());
            model.setB2bUnit(b2bUnit);
        }

        AcademicModel saved = academicRepository.save(model);
        return academicPopulator.toDTO(saved);
    }


    @Override
    public Page<AcademicDTO> getAllAcademics(Long b2bUnitId, Pageable pageable) {
        Page<AcademicModel> page = academicRepository.findByB2bUnitId(b2bUnitId, pageable);
        List<AcademicDTO> dtos = page.getContent()
                .stream()
                .map(academicPopulator::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }

    @Override
    public void assignUsers(Long academicId, AssignUserDTO dto) {
        AcademicModel academic = academicRepository.findById(academicId)
                .orElseThrow(() -> new HltCustomerException(ErrorCode.BUSINESS_NOT_FOUND));

        List<UserModel> users = userRepository.findAllById(dto.getUserIds());
        if (users.size() != dto.getUserIds().size()) {
            throw new HltCustomerException(ErrorCode.USER_NOT_FOUND);
        }

        List<AcademicUserMapping> mappings = users.stream()
                .map(u -> AcademicUserMapping.builder()
                        .academicId(academicId)
                        .userId(u.getId())
                        .role(dto.getRole())
                        .build())
                .collect(Collectors.toList());

        mappingRepository.saveAll(mappings);
    }

    @Override
    public Page<AcademicDTO> getUsersInAcademic(Long academicId, Pageable pageable) {
        List<AcademicUserMapping> mappings = mappingRepository.findByAcademicId(academicId);
        List<UserModel> users = mappings.stream()
                .map(m -> userRepository.findById(m.getUserId())
                        .orElseThrow(() -> new HltCustomerException(ErrorCode.USER_NOT_FOUND)))
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), users.size());

        List<AcademicDTO> dtos = users.subList(start, end).stream()
                .map(u -> {
                    AcademicDTO dto = new AcademicDTO();
                    dto.setId(u.getId());
                    dto.setName(u.getFullName());
                    return dto;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, users.size());
    }
}
